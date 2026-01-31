> [!CAUTION]
> The GameServer is not yet released and is NOT in a working state

[![Contributors][contributors-shield]][contributors-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Discord][discord-shield]][discord-url]

<br/>
<p align="center">
    <a href="https://brickforce-aurora.de/">
        <img src="https://raw.githubusercontent.com/Brick-Force-Aurora/Launcher/master/.github/assets/logo.png"/>
    </a>
    <h3 align="center">Brick-Force Aurora Server</h3>
    <p align="center">
        A custom server implementation for Brick-Force Aurora written from scratch in Java
        <br/><br/>
        <a href="https://github.com/Brick-Force-Aurora/GameServer/issues/new">Report Bug</a>
        ·
        <a href="https://github.com/Brick-Force-Aurora/GameServer/issues/new">Request Feature</a>
        ·
        <a href="https://brickforce-aurora.de/roadmap/">Roadmap</a>
    </p>
</p>

<details open="open">
    <summary><h3 style="display: inline-block">Table of Contents</h3></summary>
    <ol>
        <li><a href="#what-is-the-brick-force-aurora-server">What is the Brick-Force Aurora Server?</a></li>
        <li><a href="#project-goals-">Project Goals</a></li>
        <li><a href="#features-">Features</a></li>
        <li><a href="#getting-started-%EF%B8%8F">Getting Started</a></li>
    </ol>
</details>

---

### What is the Brick-Force Aurora Server?

The **Brick-Force Aurora Server** is a complete reimplementation of the original Brick-Force game server, written entirely from scratch in **Java**.

It is designed to be:
- Fully compatible with the **Brick-Force Aurora client**
- Easy to extend and modify
- Cleanly structured and maintainable

This project serves as the backend foundation for **Brick-Force Aurora**, handling networking, gameplay logic, player sessions, and more.

---

### Project Goals 🚀

- 🧱 **From-scratch implementation**  
  Implemented newly in Java

- ⚙️ **Modern Java architecture**  
  Clear separation of networking, protocol handling, game logic, and services.

- 🔌 **Extensible & modular**  
  Designed to allow easy addition of new game modes, features, and systems.

- 🛡️ **Stable & secure**  
  Focused on correctness, validation, and predictable behavior.

- 🎮 **Faithful gameplay**  
  Preserve the feel and mechanics of the original Brick-Force experience.

---

### Features 🧩

- [x] **Custom networking layer** 🌐  
  Packet-based communication with a clean listener & handler system.

- [x] **Protocol abstraction** 📦  
  Structured clientbound & serverbound packets with version awareness.

- [x] **Client & session management** 👤  
  Handles player connections, identification, and lifecycle.

- [x] **Extensible packet listeners** 🔌  
  Easily hook into login, gameplay, and system events.

- [ ] **Gameplay systems** 🎮  
  (Work in progress) Game logic such as matches, maps, weapons, and scoring.

- [ ] **Persistence & storage** 💾  
  (Planned) Player data, stats, and configuration handling.

---

### Getting Started 🛠️

> ⚠️ This project is still under active development and not yet production-ready.

#### Requirements
- **Java 17+**
- **Maven**
- A basic understanding of Java networking & backend development

#### Build
```bash
mvn clean package
```

#### Run
```bash
java.exe -jar gameserver.jar
```

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/Brick-Force-Aurora/Launcher.svg?style=flat-square&labelColor=%231D1F22&logo=devbox&logoColor=%230088cc&color=%230088cc
[contributors-url]: https://github.com/Brick-Force-Aurora/GameServer/graphs/contributors
[stars-shield]: https://img.shields.io/github/stars/Brick-Force-Aurora/Launcher.svg?style=flat-square&labelColor=%231D1F22&logo=reverbnation&logoColor=%23E3B341&color=%23E3B341
[stars-url]: https://github.com/Brick-Force-Aurora/GameServer/stargazers
[issues-shield]: https://img.shields.io/github/issues/Brick-Force-Aurora/Launcher.svg?style=flat-square&labelColor=%231D1F22&logo=buffer&logoColor=%230ec784&color=%230ec784
[issues-url]: https://github.com/Brick-Force-Aurora/GameServer/issues
[discord-shield]: https://img.shields.io/discord/777075012032004107?style=flat-square&logo=discord&logoColor=%235865f2&label=Discord&labelColor=%231D1F22&color=%23404eed
[discord-url]: https://discord.com/invite/npqB9f6xXZ
