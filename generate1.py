import os
import re
import json
from pathlib import Path
from collections import defaultdict


def load_difficulty_info(json_file="problems.json"):
    """Load difficulty information from JSON file."""
    try:
        with open(json_file, "r", encoding="utf-8") as f:
            data = json.load(f)
            # Ensure all keys are strings and values are properly formatted
            normalized_data = {}
            for key, value in data.items():
                # Store with string keys for consistent lookup
                normalized_data[str(int(key))] = value
            print(f"✓ Loaded difficulty info for {len(normalized_data)} problems")
            return normalized_data
    except FileNotFoundError:
        print(f"Warning: {json_file} not found. Using default difficulty mapping.")
        return {}
    except Exception as e:
        print(f"Error loading {json_file}: {e}")
        return {}


def parse_readme_topics(readme_file="README.md"):
    """Parse README.md and extract topics with their problems."""
    topics = {}

    with open(readme_file, "r", encoding="utf-8") as f:
        content = f.read()

    # Split by topic headers (## Topic Name)
    topic_sections = re.split(r"^## (.+)$", content, flags=re.MULTILINE)

    for i in range(1, len(topic_sections), 2):
        topic_name = topic_sections[i].strip()
        topic_content = topic_sections[i + 1]

        # Extract problem links from markdown table
        problems = []
        # Pattern: | [problem-folder](github-link) |
        pattern = r"\|\s*\[([^\]]+)\]\(https://github\.com/[^/]+/[^/]+/tree/[^/]+/([^\)]+)\)\s*\|"

        for match in re.finditer(pattern, topic_content):
            folder_name = match.group(2)
            # Extract problem number from folder name (e.g., "0001-two-sum" -> 1)
            problem_match = re.match(r"^(\d+)-", folder_name)
            if problem_match:
                # Convert to int to remove leading zeros (0001 -> 1)
                problem_num = int(problem_match.group(1))
                problems.append({"num": problem_num, "folder": folder_name})

        if problems:
            topics[topic_name] = problems

    return topics


def get_problem_name_from_folder(folder_name):
    """Convert folder name to readable problem name."""
    # Remove leading numbers and hyphens
    name = re.sub(r"^\d+-", "", folder_name)
    # Replace hyphens with spaces and title case
    name = name.replace("-", " ").title()
    return name


def get_code_files(folder_path):
    """Get all code files from problem folder."""
    code_extensions = {
        ".py": "python",
        ".cpp": "cpp",
        ".java": "java",
        ".js": "javascript",
        ".go": "go",
        ".rs": "rust",
        ".c": "c",
        ".cs": "csharp",
        ".rb": "ruby",
        ".kt": "kotlin",
        ".swift": "swift",
    }

    code_files = {}

    if not folder_path.exists():
        return code_files

    for ext, lang in code_extensions.items():
        files = list(folder_path.glob(f"*{ext}"))
        if files:
            try:
                code_files[lang] = files[0].read_text(encoding="utf-8")
            except:
                continue

    return code_files


def read_problem_readme(folder_path):
    """Read README.md from problem folder if exists."""
    readme_path = folder_path / "README.md"
    if readme_path.exists():
        try:
            return readme_path.read_text(encoding="utf-8")
        except:
            return None
    return None


def create_problem_page(
    problem_num, problem_name, folder_name, difficulty, code_files, readme_content
):
    """Create individual problem markdown page."""

    difficulty_colors = {"Easy": "success", "Medium": "warning", "Hard": "danger"}

    difficulty_color = difficulty_colors.get(difficulty, "info")

    # Format problem URL (LeetCode standard format)
    problem_slug = folder_name.replace(f"{problem_num:04d}-", "")
    leetcode_url = f"https://leetcode.com/problems/{problem_slug}/"
    github_url = f"https://github.com/RAJ8664/Leetcode/tree/master/{folder_name}"

    content = f"""# {problem_num}. {problem_name}

!!! {difficulty_color} "Difficulty: {difficulty}"

[:octicons-link-external-24: LeetCode Problem]({leetcode_url}){{ .md-button }}
[:octicons-code-24: View on GitHub]({github_url}){{ .md-button }}

---

"""

    # Add problem description from README if exists
    if readme_content:
        content += f"{readme_content}\n\n---\n\n"

    # Add code solutions
    if code_files:
        content += "## Solution\n\n"

        if len(code_files) == 1:
            lang, code = list(code_files.items())[0]
            content += f"```{lang}\n{code}\n```\n\n"
        else:
            # Multiple languages - use tabs
            for i, (lang, code) in enumerate(code_files.items()):
                if i == 0:
                    content += f'=== "{lang.title()}"\n\n'
                else:
                    content += f'=== "{lang.title()}"\n\n'
                content += f"    ```{lang}\n"
                for line in code.split("\n"):
                    content += f"    {line}\n"
                content += "    ```\n\n"
    else:
        content += """## Solution

*Code solution will be added here*

"""

    # Add complexity section
    content += """## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

"""

    return content


def generate_docs():
    """Main function to generate all documentation."""

    print("🚀 Starting documentation generation...\n")

    root = Path(".")
    docs_problems = Path("docs/problems")
    docs_problems.mkdir(parents=True, exist_ok=True)

    # Load difficulty information
    print("📖 Loading difficulty information from info.json...")
    difficulty_info = load_difficulty_info()

    # Parse README.md for topics
    print("📋 Parsing README.md for topics and problems...")
    topics = parse_readme_topics()

    print(f"✓ Found {len(topics)} topics\n")

    # Organize problems by difficulty
    problems_by_difficulty = defaultdict(list)
    all_problems = {}

    # Process each topic
    for topic_name, problems in topics.items():
        print(f"Processing topic: {topic_name}")

        for problem in problems:
            problem_num = problem["num"]
            folder_name = problem["folder"]

            # Skip if already processed
            if problem_num in all_problems:
                continue

            # Get difficulty from JSON - use string key for lookup
            problem_num_str = str(problem_num)
            difficulty = difficulty_info.get(problem_num_str, None)

            # If not found, try without leading zeros or default to Medium
            if difficulty is None:
                print(
                    f"  ⚠️  Warning: Difficulty not found for problem {problem_num} ({folder_name}), defaulting to Medium"
                )
                difficulty = "Medium"

            print(f"  → Problem {problem_num}: {difficulty}")

            problem_name = get_problem_name_from_folder(folder_name)
            folder_path = root / folder_name

            # Get code files and README
            code_files = get_code_files(folder_path)
            readme_content = read_problem_readme(folder_path)

            # Create individual problem page
            problem_page = create_problem_page(
                problem_num,
                problem_name,
                folder_name,
                difficulty,
                code_files,
                readme_content,
            )

            problem_file = docs_problems / f"{folder_name}.md"
            problem_file.write_text(problem_page, encoding="utf-8")

            # Store problem info
            problem_info = {
                "num": problem_num,
                "name": problem_name,
                "folder": folder_name,
                "difficulty": difficulty,
                "topics": [],
            }

            if problem_num not in all_problems:
                all_problems[problem_num] = problem_info

            all_problems[problem_num]["topics"].append(topic_name)
            problems_by_difficulty[difficulty].append(problem_info)

        print(f"  ✓ Processed {len(problems)} problems")

    print("\n📝 Generating difficulty-based pages...")

    # Generate difficulty pages
    for difficulty in ["Easy", "Medium", "Hard"]:
        probs = sorted(problems_by_difficulty[difficulty], key=lambda x: x["num"])

        if not probs:
            continue

        difficulty_emoji = {"Easy": "🟢", "Medium": "🟡", "Hard": "🔴"}

        content = f"# {difficulty_emoji[difficulty]} {difficulty} Problems\n\n"
        content += f"**Total: {len(probs)} problems**\n\n"
        content += "| # | Problem | Topics | Difficulty |\n"
        content += "|:---:|---------|--------|:----------:|\n"

        for prob in probs:
            topics_str = ", ".join(prob["topics"][:3])  # Show first 3 topics
            if len(prob["topics"]) > 3:
                topics_str += f" (+{len(prob['topics']) - 3} more)"

            content += (
                f"| {prob['num']} | "
                f"[{prob['name']}]({prob['folder']}.md) | "
                f"{topics_str} | "
                f"{difficulty} |\n"
            )

        category_file = docs_problems / f"{difficulty.lower()}.md"
        category_file.write_text(content, encoding="utf-8")
        print(f"  ✓ Generated {difficulty.lower()}.md with {len(probs)} problems")

    # Generate topics index
    print("\n📚 Generating topics index...")
    topics_content = "# Problems by Topic\n\n"
    topics_content += "Browse problems organized by their topics/tags.\n\n"

    for topic_name in sorted(topics.keys()):
        topic_problems = topics[topic_name]
        topics_content += f"## {topic_name}\n\n"
        topics_content += f"**{len(topic_problems)} problems**\n\n"

        # Group by difficulty
        topic_by_diff = defaultdict(list)
        for prob in topic_problems:
            prob_num = prob["num"]
            if prob_num in all_problems:
                topic_by_diff[all_problems[prob_num]["difficulty"]].append(
                    all_problems[prob_num]
                )

        for difficulty in ["Easy", "Medium", "Hard"]:
            if difficulty in topic_by_diff:
                probs = sorted(topic_by_diff[difficulty], key=lambda x: x["num"])
                topics_content += f"\n**{difficulty}**: "
                problem_links = [f"[{p['num']}]({p['folder']}.md)" for p in probs]
                topics_content += ", ".join(problem_links) + "\n"

        topics_content += "\n---\n\n"

    topics_file = docs_problems / "topics.md"
    topics_file.write_text(topics_content, encoding="utf-8")
    print("  ✓ Generated topics.md")

    # Update statistics
    print("\n📊 Updating statistics...")
    stats = {
        "total": len(all_problems),
        "easy": len(problems_by_difficulty["Easy"]),
        "medium": len(problems_by_difficulty["Medium"]),
        "hard": len(problems_by_difficulty["Hard"]),
    }

    print("\n" + "=" * 50)
    print("✅ Documentation generation complete!")
    print("=" * 50)
    print(f"\n📈 Statistics:")
    print(f"   Total Problems: {stats['total']}")
    print(f"   🟢 Easy: {stats['easy']} problems")
    print(f"   🟡 Medium: {stats['medium']} problems")
    print(f"   🔴 Hard: {stats['hard']} problems")
    print(f"   📑 Topics: {len(topics)}")
    print("\n💡 Next steps:")
    print("   1. Run: mkdocs serve")
    print("   2. Visit: http://127.0.0.1:8000")
    print("   3. Deploy: mkdocs gh-deploy")
    print()


if __name__ == "__main__":
    try:
        generate_docs()
    except Exception as e:
        print(f"\n❌ Error: {e}")
        import traceback

        traceback.print_exc()
