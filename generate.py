import os
import re
from pathlib import Path


def get_problem_info(folder_name):
    """Extract problem number and name from folder."""
    match = re.match(r"^(\d+)-(.+)$", folder_name)
    if match:
        num = int(match.group(1))
        name = match.group(2).replace("-", " ").title()
        return num, name
    return None, None


def read_problem_readme(folder_path):
    """Read README.md from problem folder if exists."""
    readme_path = folder_path / "README.md"
    if readme_path.exists():
        return readme_path.read_text(encoding="utf-8")
    return None


def categorize_difficulty(problem_num):
    """Categorize problem difficulty based on number (approximate)."""
    # You can customize this logic or read from a JSON file
    # This is a simple heuristic
    if problem_num <= 300:
        return "easy"
    elif problem_num <= 600:
        return "medium"
    else:
        return "hard"


def get_code_files(folder_path):
    """Get all code files from problem folder."""
    code_extensions = {
        ".py": "Python",
        ".cpp": "C++",
        ".java": "Java",
        ".js": "JavaScript",
        ".go": "Go",
        ".rs": "Rust",
    }
    code_files = {}

    for ext, lang in code_extensions.items():
        files = list(folder_path.glob(f"*{ext}"))
        if files:
            code_files[lang] = files[0].read_text(encoding="utf-8")

    return code_files


def create_problem_page(
    problem_num, problem_name, folder_name, difficulty, code_files, readme_content
):
    """Create individual problem markdown page."""

    # Format problem number with leading zeros
    num_str = f"{problem_num:04d}"

    content = f"""# {problem_num}. {problem_name}

---

"""

    # Add problem description from README if exists
    if readme_content:
        content += f"{readme_content}\n\n"
    else:
        content += """## Problem

[Add problem description here]

"""

    # Add code solutions
    if code_files:
        content += "## Solution\n\n"

        if len(code_files) == 1:
            lang, code = list(code_files.items())[0]
            content += f"```{lang.lower()}\n{code}\n```\n\n"
        else:
            # Multiple languages - use tabs
            for i, (lang, code) in enumerate(code_files.items()):
                if i == 0:
                    content += f'=== "{lang}"\n'
                else:
                    content += f'\n=== "{lang}"\n'
                content += f"    ```{lang.lower()}\n"
                for line in code.split("\n"):
                    content += f"    {line}\n"
                content += "    ```\n"
            content += "\n"

    # Add complexity section
    content += """## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

"""

    return content


def generate_docs():
    """Main function to generate all documentation."""

    root = Path(".")
    docs_problems = Path("docs/problems")
    docs_problems.mkdir(parents=True, exist_ok=True)

    # Find all problem folders
    problem_folders = [
        f for f in root.iterdir() if f.is_dir() and re.match(r"^\d{4}-", f.name)
    ]

    problems = {"easy": [], "medium": [], "hard": []}

    print(f"Found {len(problem_folders)} problem folders")

    for folder in sorted(problem_folders):
        problem_num, problem_name = get_problem_info(folder.name)

        if problem_num is None:
            continue

        difficulty = categorize_difficulty(problem_num)
        code_files = get_code_files(folder)
        readme_content = read_problem_readme(folder)

        # Create individual problem page
        problem_page = create_problem_page(
            problem_num,
            problem_name,
            folder.name,
            difficulty,
            code_files,
            readme_content,
        )

        problem_file = docs_problems / f"{folder.name}.md"
        problem_file.write_text(problem_page, encoding="utf-8")

        # Add to category list
        problems[difficulty].append(
            {
                "num": problem_num,
                "name": problem_name,
                "folder": folder.name,
                "difficulty": difficulty,
            }
        )

        print(f"✓ Generated {folder.name}.md")

    # Generate category pages
    for difficulty, probs in problems.items():
        content = f"# {difficulty.title()} Problems\n\n"
        content += f"Total: **{len(probs)}** problems\n\n"
        content += "| # | Problem | Difficulty |\n"
        content += "|---|---------|------------|\n"

        for prob in sorted(probs, key=lambda x: x["num"]):
            badge_class = f"difficulty-{prob['difficulty']}"
            content += (
                f"| {prob['num']} | "
                f"[{prob['name']}]({prob['folder']}.md) | "
                f"<span class='{badge_class}'>{prob['difficulty'].title()}</span> |\n"
            )

        category_file = docs_problems / f"{difficulty}.md"
        category_file.write_text(content, encoding="utf-8")
        print(f"✓ Generated {difficulty}.md with {len(probs)} problems")

    print("\n✅ Documentation generation complete!")
    print(f"   Easy: {len(problems['easy'])} problems")
    print(f"   Medium: {len(problems['medium'])} problems")
    print(f"   Hard: {len(problems['hard'])} problems")


if __name__ == "__main__":
    generate_docs()
