# Simple HTTP Server

A lightweight, custom-built HTTP server implemented completely from scratch in Java. This project demonstrates the core mechanics of web servers by utilizing raw TCP sockets, custom message parsing, and multi-threading, all without relying on heavyweight external web frameworks. 

## Features

- Pure Java Implementation: Built using standard Java networking libraries and core socket programming techniques.
- Multithreaded Architecture: Efficiently handles multiple concurrent client connections by dispatching requests to dedicated worker threads.
- Custom HTTP Parser: Reads and decodes incoming raw HTTP streams, accurately extracting HTTP methods, targets, versions, headers, and body content.
- Protocol Adherence: Generates structurally valid HTTP responses complete with appropriate status codes (e.g., 200 OK, 404 Not Found, 500 Server Error).
- Static File Serving: Automatically resolves, reads, and serves static web assets (HTML, CSS, text) from a designated web root directory.
- Dynamic Configuration: Centralized server setup using JSON configuration files to easily manage ports and web root paths.
- Modular Design: Clean separation of concerns between server initialization, connection management, request handling, and file system operations.

## Tech Stack

- Language: Java
- Build Tool: Maven
- Configuration Parsing: Jackson (JSON)
- Networking: Java `java.net.Socket` and `java.net.ServerSocket`

## Getting Started

Follow these instructions to set up the HTTP server on your local machine for development and testing.

### Prerequisites

Ensure you have the following installed on your local environment:
- Java Development Kit (JDK) 11 or higher
- Apache Maven

### Installation

1. Clone the repository:
   ```bash
   git clone [https://github.com/samiyun/simple-http-server.git](https://github.com/samiyun/simple-http-server.git)
