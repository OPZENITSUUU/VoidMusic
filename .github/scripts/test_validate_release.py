from __future__ import annotations

import subprocess
import tempfile
import unittest
from pathlib import Path

from validate_release import (
    ValidationError,
    extract_gradle_version,
    parse_tag,
    previous_tag_version_code,
    validate_release,
)


def gradle_source(name: str = "1.0.11", code: int = 42) -> str:
    return (
        f'extra["bitchordVersionCode"] = {code}\n'
        f'extra["bitchordVersionName"] = "{name}"\n'
    )


class ReleaseValidatorTest(unittest.TestCase):
    def test_accepts_plain_semver_release_tag(self) -> None:
        self.assertEqual((1, 0, 11), parse_tag("v1.0.11"))

    def test_rejects_non_release_tag_syntax(self) -> None:
        for tag in ("1.0.11", "v1.0", "v1.0.11-beta1", "v01.0.11"):
            with self.subTest(tag=tag), self.assertRaises(ValidationError):
                parse_tag(tag)

    def test_extracts_gradle_version_name_and_code(self) -> None:
        self.assertEqual(("1.0.11", 42), extract_gradle_version(gradle_source()))

    def test_rejects_missing_gradle_version_declarations(self) -> None:
        with self.assertRaises(ValidationError):
            extract_gradle_version("plugins { }\n")

    def test_rejects_tag_that_differs_from_gradle_version_name(self) -> None:
        with self.assertRaisesRegex(ValidationError, "does not match"):
            validate_release("v1.0.11", gradle_source(name="1.0.10"))

    def test_requires_version_code_to_increase(self) -> None:
        with self.assertRaisesRegex(ValidationError, "must exceed"):
            validate_release("v1.0.11", gradle_source(code=41), previous_version_code=41)

    def test_accepts_monotonic_version_code(self) -> None:
        release = validate_release("v1.0.11", gradle_source(), previous_version_code=41)
        self.assertEqual(42, release.version_code)

    def test_rejects_tag_not_newer_than_latest_published_release(self) -> None:
        with self.assertRaisesRegex(ValidationError, "not newer"):
            validate_release("v1.0.10", gradle_source(name="1.0.10"), latest_release_tag="v1.0.10")

    def test_rejects_existing_release(self) -> None:
        with self.assertRaisesRegex(ValidationError, "already exists"):
            validate_release("v1.0.11", gradle_source(), release_already_exists=True)

    def test_reads_code_from_most_recent_prior_git_tag(self) -> None:
        with tempfile.TemporaryDirectory() as temp:
            root = Path(temp)
            subprocess.run(["git", "init", "-q"], cwd=root, check=True)
            subprocess.run(["git", "config", "user.email", "test@example.invalid"], cwd=root, check=True)
            subprocess.run(["git", "config", "user.name", "Release Test"], cwd=root, check=True)
            gradle = root / "build.gradle.kts"
            gradle.write_text(gradle_source(name="1.0.10", code=41), encoding="utf-8")
            subprocess.run(["git", "add", "build.gradle.kts"], cwd=root, check=True)
            subprocess.run(["git", "commit", "-qm", "tag fixture"], cwd=root, check=True)
            subprocess.run(["git", "tag", "v1.0.10"], cwd=root, check=True)
            self.assertEqual(41, previous_tag_version_code("v1.0.11", root, "build.gradle.kts"))


if __name__ == "__main__":
    unittest.main()
