// This script generates problems.json(all the problems number with their difficulty) from leetcode.com API

import fs from 'fs'
import fetch from 'node-fetch'

async function main() {
    try {
        const res = await fetch('https://leetcode.com/api/problems/all/')
        const json = await res.json()

        const problems = {}

        json.stat_status_pairs.forEach((p) => {
            const id = p.stat.question_id
            const slug = p.stat.question__title_slug
            const difficulty =
                ['Easy', 'Medium', 'Hard'][p.difficulty.level - 1] || 'Medium'
            problems[id] = difficulty
        })

        if (!fs.existsSync('./')) fs.mkdirSync('./')

        fs.writeFileSync('./problems.json', JSON.stringify(problems, null, 2))
        console.log('✅ problems.json generated successfully!')
    } catch (err) {
        console.error('❌ Failed to generate problems.json:', err)
    }
}

main()
