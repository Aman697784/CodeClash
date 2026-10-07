package com.example.codeclash

import com.example.codeclash.models.Question

object QuestionBank {

    val programming = listOf(
        Question(
            "Which language is mainly used for Android development?",
            "Python",
            "Kotlin",
            "HTML",
            "SQL",
            "B"
        ),
        Question(
            "Which data structure uses FIFO?",
            "Stack",
            "Queue",
            "Tree",
            "Graph",
            "B"
        ),
        Question(
            "Which symbol is used for a single-line comment in Kotlin?",
            "//",
            "/*",
            "#",
            "<!--",
            "A"
        ),
        Question(
            "What does OOP stand for?",
            "Object Oriented Programming",
            "Online Object Program",
            "Open Operating Program",
            "Object Operating Process",
            "A"
        ),
        Question(
            "Which one is a programming language?",
            "HTML",
            "CSS",
            "Kotlin",
            "XML",
            "C"
        )
    )

    val networking = listOf(
        Question(
            "Which protocol is used for secure web communication?",
            "HTTP",
            "FTP",
            "HTTPS",
            "SMTP",
            "C"
        ),
        Question(
            "What does IP stand for?",
            "Internet Protocol",
            "Internal Program",
            "Internet Process",
            "Information Protocol",
            "A"
        ),
        Question(
            "Which device connects different networks?",
            "Switch",
            "Router",
            "Hub",
            "Repeater",
            "B"
        )
    )

    val database = listOf(
        Question(
            "Which database are we using in Code Clash?",
            "MySQL",
            "Oracle",
            "Firebase",
            "MongoDB",
            "C"
        ),
        Question(
            "What does SQL stand for?",
            "Structured Query Language",
            "Simple Query Language",
            "System Query Logic",
            "Structured Question Language",
            "A"
        )
    )

    val questions = listOf(
        Question(
            "Which data structure follows LIFO?",
            "Queue",
            "Stack",
            "Array",
            "Linked List",
            "B"
        ),
        Question(
            "Which data structure follows FIFO?",
            "Stack",
            "Tree",
            "Queue",
            "Graph",
            "C"
        ),
        Question(
            "What is the time complexity of Binary Search?",
            "O(n)",
            "O(n²)",
            "O(log n)",
            "O(1)",
            "C"
        ),
        Question(
            "Which keyword declares a read-only variable in Kotlin?",
            "var",
            "let",
            "const",
            "val",
            "D"
        ),
        Question(
            "What does CPU stand for?",
            "Central Processing Unit",
            "Computer Processing Utility",
            "Central Program Unit",
            "Control Processing Unit",
            "A"
        ),
        Question(
            "Which SQL command retrieves data?",
            "INSERT",
            "UPDATE",
            "SELECT",
            "DELETE",
            "C"
        ),
        Question(
            "Which OOP concept allows the same method name to have different behavior?",
            "Encapsulation",
            "Inheritance",
            "Polymorphism",
            "Abstraction",
            "C"
        ),
        Question(
            "What is the output of 5 + 3 * 2?",
            "16",
            "11",
            "13",
            "10",
            "B"
        ),
        Question(
            "What does API stand for?",
            "Application Programming Interface",
            "Application Process Integration",
            "Advanced Programming Internet",
            "Automated Program Interface",
            "A"
        ),
        Question(
            "Which Android component is commonly used for efficient scrollable lists?",
            "TextView",
            "RecyclerView",
            "ImageView",
            "WebView",
            "B"
        ),
        Question(
            "Which HTTP status code means Not Found?",
            "200",
            "301",
            "404",
            "500",
            "C"
        ),
        Question(
            "Which Git command downloads a repository?",
            "git push",
            "git clone",
            "git commit",
            "git merge",
            "B"
        ),
        Question(
            "What does JSON primarily represent?",
            "Image data",
            "Structured data",
            "Machine code",
            "Executable files",
            "B"
        ),
        Question(
            "Which algorithm finds the shortest path with non-negative edge weights?",
            "Dijkstra's Algorithm",
            "Bubble Sort",
            "Binary Search",
            "DFS",
            "A"
        ),
        Question(
            "Which Firebase service provides real-time JSON-like data synchronization?",
            "Firebase Storage",
            "Firebase Realtime Database",
            "Firebase Hosting",
            "Crashlytics",
            "B"
        )
    )

    val allQuestions: List<Question>
        get() = programming + networking + database + questions
}
