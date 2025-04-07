# 🚀 Scala Learning Repository

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Code of Conduct](https://img.shields.io/badge/Code%20of%20Conduct-Contributor%20Covenant-ff69b4.svg)](CODE_OF_CONDUCT.md)

This repository is designed to help you grasp the fundamentals of the Scala programming language, including its syntax and core concepts. It contains a collection of interactive notebooks and Scala exercises to guide you through the learning process.

## 📚 Contents

This repository is structured into the following sections:

* **`notebooks/`**: Contains Jupyter notebooks that provide explanations, examples, and interactive exercises for various Scala topics.
* **`Scala_basics/`**: Features Scala files (`.scala`) with basic programming exercises to practice fundamental syntax and concepts.
* **`Scala_OOP/`**: Includes Scala files focusing on Object-Oriented Programming (OOP) principles in Scala, along with some functional programming exercises.

### 💻 Project structure

The `Scala_basics/` and `Scala_OOP/` directories contain Scala source files (`.scala`) with exercises designed to reinforce your understanding of the language:

```bash
.
├── CODE_OF_CONDUCT.md
├── LICENSE
├── notebooks
│   ├── 01-Getting_started_exercise.ipynb # Exercises to get you started with Scala.
│   ├── 01-Getting_started.ipynb # Introduction to the Scala language and setting up your environment.
│   ├── 01-Object_oriented_programming_exercise.ipynb # Exercises to practice Object-Oriented Programming concepts in Scala.
│   ├── 01-Object_oriented_programming.ipynb # Explanation of Object-Oriented Programming principles in Scala.
│   ├── 02-Basics_of_Scala.ipynb # Delving deeper into the basic syntax and data types of Scala.
│   ├── 02-Functional_programming.ipynb # Introduction to Functional Programming concepts in Scala.
│   ├── 02-Packages_and_imports.ipynb # Understanding how to organize code with packages and manage dependencies with imports.
│   ├── 02-The_basics_of_scala.ipynb # Another perspective on the fundamental aspects of the Scala language.
│   ├── 03-Functionnal_programming.ipynb # Further exploration of Functional Programming techniques in Scala.
│   ├── 03-Working_with_an_ide.ipynb # Guidance on setting up and using an Integrated Development Environment (IDE) for Scala development.
│   └── 04-Design_patterns.ipynb # Introduction to common design patterns and their implementation in Scala.
├── README.md
├── Scala_basics
│   ├── Exercise0.scala
│   ├── Exercise1.scala
│   ├── Exercise2.scala
│   └── Exercise3.scala
├── Scala_OOP
│   ├── FP_Exercise0.scala
│   ├── FP_Exercise1.scala
│   ├── FP_Exercise2.scala
│   ├── OOP-Exercise0.scala
│   ├── OOP-Exercise1.scala
│   └── Palindrome.scala
└── SECURITY.md
```

These exercises cover a range of topics, from basic syntax to object-oriented and functional programming paradigms.

## 🛠️ Getting Started

To start learning with this repository:

1. **Clone the repository:**

   ```bash
   git clone <YOUR_REPOSITORY_URL>
   cd <YOUR_REPOSITORY_NAME>
   ```
2. **Install Scala and sbt (if you haven't already):**

   * You can follow the official Scala documentation for installation instructions: [https://www.scala-lang.org/download/](https://www.scala-lang.org/download/)
   * sbt (Simple Build Tool) is commonly used for building Scala projects. Installation instructions can be found here: [https://www.scala-sbt.org/download.html](https://www.scala-sbt.org/download.html)
3. **Explore the notebooks:**

   * Ensure you have Jupyter installed (`pip install notebook`).
   * Navigate to the `notebooks/` directory in your terminal and run `jupyter notebook`.
   * Open the notebooks in your browser and follow along with the explanations and exercises.
4. **Work through the Scala exercises:**

   * Navigate to the `Scala_basics/` or `Scala_OOP/` directories.
   * You can compile and run the Scala files using the `scalac` compiler and the `scala` command, or by using sbt. For example:

     ```bash
     scalac Scala_basics/Exercise0.scala
     scala Scala_basics.Exercise0
     ```

     Or, if you have sbt in the project root:

     ```bash
     sbt run
     ```

     (You might need to configure an sbt project definition if you choose this route for individual files).

## 📜 License

This project is licensed under the [MIT](LICENSE) license. See the [LICENSE](LICENSE) file for more details.

## 🤝 Contributing

Contributions are welcome! Please review the [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) file for contribution guidelines.

## 🛡️ Security

Please refer to the [SECURITY.md](SECURITY.md) file for security guidelines.

---

**Happy learning Scala!** 🚀
