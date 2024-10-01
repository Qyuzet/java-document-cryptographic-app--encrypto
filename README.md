## Introducing Encrypto: Secure Your Word Files with Ease!


### Encrypto: A Java-Based Solution for Secure Document Encryption
**Download Link** : https://fastupload.io/a8addea77dc10b70

This project introduces Encrypto, a Java-based application designed to enhance the security of Microsoft Word documents through encryption and decryption functionalities
![e-ff](https://github.com/Qyuzet/java-document-cryptographic-app--encrypto/assets/93258081/2af50ef0-fd13-4177-908e-1f4c62d8bb4d)


**Key Features**

*   **Robust Encryption:** Employs the Advanced Encryption Standard (AES) algorithm, a widely recognized and secure encryption standard, to safeguard document content...
*   **User-Friendly Interface:** Provides a simple and intuitive graphical user interface (GUI) built with Java Swing, making encryption and decryption processes accessible to users.
*   **Seamless Word Integration:** Utilizes the Apache POI library to interact with Word documents (.docx), enabling the extraction, modification, and saving of encrypted content within the document structure..
*   **Base64 Encoding:** Converts encrypted binary data into a text-based format using Base64 encoding, ensuring compatibility with the text-based nature of Word documents.
*   **Efficient Development:** Leverages Maven for build automation and dependency management, streamlining the development process and ensuring project consistency.
*   **Cross-Platform Compatibility:** Developed in Java, the application is designed to run on various operating systems, maximizing its reach and usability.
*   **Standalone Executable:** Utilizes Launch4j to package the application into a standalone executable, simplifying distribution and eliminating the need for users to have Java installed.

**How Encrypto Works**

![image](https://github.com/Qyuzet/java-document-cryptographic-app--encrypto/assets/93258081/d59540f0-5ef2-49e1-8b21-9fc7d7cebe90)


1.  **User Selection:** The user chooses to either encrypt or decrypt Word documents within a specified folder.
2.  **Key Management:** The application handles the generation or loading of the secret key used for encryption and decryption.
3.  **Encryption:**
    *   Plaintext Extraction: The original content of the Word document is extracted.
    *   AES Encryption: The extracted text is encrypted using the AES algorithm and the secret key.
    *   Base64 Encoding: The encrypted binary data is converted to Base64 format.
    *   Text Replacement: The original text in the document is replaced with the Base64-encoded encrypted text.
    *   Document Saving: The modified document, now containing encrypted content, is saved.
4.  **Decryption:**
    *   Encrypted Text Retrieval: The Base64-encoded encrypted text is retrieved from the document.
    *   Base64 Decoding: The encoded text is converted back to binary form.
    *   AES Decryption: The binary data is decrypted using the AES algorithm and the secret key.
    *   Text Replacement: The encrypted text is replaced with the original decrypted content.
    *   Document Saving: The restored document is saved.

**Data Structure Performance**


The project evaluated the performance of different data structures (linked lists, arrays, and hashmaps) for encryption and decryption tasks. Hashmaps emerged as the most efficient choice, offering a balance of speed and memory usage.


**Class Diagram**


![Main_Menu](https://github.com/Qyuzet/java-document-cryptographic-app--encrypto/assets/93258081/58e461c3-8d23-4fbf-9c8e-aa97693e2ef3)
![Main_Menu_](https://github.com/Qyuzet/java-document-cryptographic-app--encrypto/assets/93258081/506eed8c-f001-4991-aa71-9fece2c400b1)


**Project Team**

*   Ari Jaya Teguh (2702403996) - Developer
*   Michael Lee Koesumo (2802470553) - Developer
*   Riki Awal Syahputra (2802471404) - Developer Leader

**Disclaimer:**

This project is primarily an educational endeavor and may not encompass all the complexities of real-world document encryption systems.

**License:**

This project is licensed under the MIT License.

![1](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/56d004be-6488-44c0-bd26-83a6fc4aa181)
![2](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/5671d8c8-9afb-470a-a745-35a8d679819f)
![3](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/17badc0f-63dc-4ad0-b504-e3854b7e0e8b)
![4](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/75d94d6e-5fba-49b2-b9e8-4c3813fe286f)
![5](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/f74ec02e-cd9a-485a-8ab8-c620e5caa280)
![6](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/c942034d-0ba4-47f2-836f-779853ea0852)
![7](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/aa611823-4109-4aa8-8c8b-e7d8e28c7f1f)
![8](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/354e775b-54b4-40aa-ae74-cbb3cc41240d)
![9](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/8b3ea920-6ecc-44a5-9840-c9ab45ab1895)
![10](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/4207c298-7154-4a91-8131-581cc7232c70)
![11](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/fee3582c-b5bc-4edc-9574-0c062a1e0e25)
![12](https://github.com/Qyuzet/ENCRYPTO/assets/93258081/b4474d1a-364e-4a4d-878f-97a2733e7eee)
