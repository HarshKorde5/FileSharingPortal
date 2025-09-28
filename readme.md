# File Sharing Application (Java, Linux)

A simple **file sharing application** built in Java as a beginner-level learning project.  
It allows two laptops connected via Wi-Fi/Hotspot to **send and receive files** over sockets, with a **basic GUI** (AWT + Swing).  
The project also makes use of **multi-threading** so the GUI remains responsive while sending/receiving files.  

---

## Features
-  Wi-Fi / Hotspot configuration using `nmcli` (Linux only).  
- Simple IP discovery within the local network.  
- end and receive files between two laptops.  
- Basic graphical interface (AWT + Swing).  
- Multi-threading for smooth file transfer and GUI handling.  
- Beginner-friendly socket communication (TCP).  

---

## 🛠️ Technologies Used
- **Java** (Core, AWT, Swing, Sockets, Multi-threading)  
- **Linux CLI** (`nmcli` for Wi-Fi/Hotspot setup)  
- **Networking** (IP discovery, socket-based transfer)  

---

## 📂 Project Structure

> All source files are inside the `src/` folder.  

---

## ⚙️ Prerequisites
- Linux laptop/PC with Wi-Fi capability.  
- Java (JDK 8 or above).  
- Two laptops connected to the same Wi-Fi/Hotspot.  

---

## 📥 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/HarshKorde5/FileSharingPortal
   cd FileSharingPortal/src
   ```

2. Compile the project:
   ```bash
   javac *.java
   ```

3. Start the **receiver** (on Laptop B):
   ```bash
   java Receiver
   ```

4. Start the **sender** (on Laptop A):
   ```bash
   java Sender
   ```

5. Use the **GUI** to select a file and send it.  

---

## 📸 Screenshots (Optional)
*(Add screenshots of your GUI here if you have any)*  

---

## ⚠️ Limitations
- Works only on Linux (because of `nmcli`).  
- No encryption (files are sent in plain form).  
- Only one file transfer at a time.  
- Limited error handling.  

---

## 🌱 Future Improvements
- Cross-platform support (Windows/Mac).  
- Add file encryption for secure transfer.  
- Support multiple file transfers and queues.  
- Add transfer progress bar and estimated time.  
- Better error handling & retry mechanism.  

---

## 🎯 Learning Outcomes
This project helped me understand:  
- Basics of Java networking (Sockets, IP).  
- GUI programming with AWT & Swing.  
- Multi-threading to handle background tasks without freezing the UI.  
- Executing Linux system commands (`nmcli`) from Java.  
- Client-server architecture fundamentals.  

---


**Contributing**

Contributions to this project are welcome! If you encounter any issues or have suggestions for improvements, please feel free to submit a pull request.



**Contact**

📧 Email: harshkorde05@gmail.com 

🌐 LinkedIn: [linkedin.com/in/harshkorde](https://www.linkedin.com/in/harshkorde)

For any further questions or inquiries, feel free to reach out. We are happy to assist you with any queries.
