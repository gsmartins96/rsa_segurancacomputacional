package client;

import java.awt.Dimension;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import rsa.EncodeDecode;
import rsa.EncryptDecrypt;
import rsa.GenerateKeys;
import rsa.Sign;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Object[] options = {"Visualizar Chave Pública", // Option 3
			                "Visualizar Chave Privada", // Option 1
			                "Digitar Mensagem"}; // Option 2
		
		Object[] options2 = {"Iniciar novamente", // Option 3
			                "Descriptar mensagem", // Option 1
			                "Encriptar mensagem"}; // Option 2
		
		JTextArea textArea = new JTextArea(6, 25);
		Boolean exit = false;
		Boolean receiveMessage = false;
		Boolean generate_again = true;
		
		BigInteger[] public_key = null;
		BigInteger[] private_key = null;
		BigInteger encoded_message;
		BigInteger encrypted_message;
		BigInteger decrypted_message;
		String message = null;
		String sign_message;
		String decoded_message;
		
		GenerateKeys generateKeys = new GenerateKeys();
		EncodeDecode encodeMessage = new EncodeDecode();
		EncodeDecode decodeMessage = new EncodeDecode();
		EncryptDecrypt encrypt = new EncryptDecrypt();
		EncryptDecrypt decrypt = new EncryptDecrypt();
		Sign sign = new Sign();
		
		JOptionPane.showMessageDialog(null,"Chaves públicas e privada Gerada");    
		
		while (exit != true) {
			if (generate_again == true) {
				public_key = generateKeys.getPublicKey();
				private_key = generateKeys.getPrivateKey();
				generate_again = false;
			}
			
			int option = JOptionPane.showOptionDialog(null,
							"Todas as chaves já foram geradas. O que deseja fazer?",
							"Segurança Computacional",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[2]);
			
			if(option == 1) {	
				// PRIVATE KEY
				String private_d = private_key[2].toString();
				textArea.setText(private_d);
				textArea.setEditable(false);
				
				JScrollPane scrollPane = new JScrollPane(textArea);
				
				JOptionPane.showMessageDialog(null, scrollPane);
			} else if (option == 2) {
				// INPUT MESSAGE
				message = JOptionPane.showInputDialog("Digite a frase: ");
				
				receiveMessage = true;
				exit = true;
			} else {
				// PUBLIC KEY
				String public_e = public_key[1].toString();
				textArea.setText(public_e);
				textArea.setEditable(false);
				
				JScrollPane scrollPane = new JScrollPane(textArea);
				
				JOptionPane.showMessageDialog(null, scrollPane);
			}
			
			while(receiveMessage == true) {
				encoded_message = encodeMessage.encode(message);
				encrypted_message = encrypt.encrypt(public_key, encoded_message);
				decrypted_message = decrypt.decrypt(private_key, public_key, encrypted_message);
				decoded_message = decodeMessage.decode(decrypted_message);
				sign_message = sign.sign(private_key, public_key, message, encoded_message);
				
				int option2 = JOptionPane.showOptionDialog(null,
						"Mensagem armazenada. O que deseja fazer?",
						"Segurança Computacional",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[2]);
				
				if(option2 == 1) {	
					// DECRYPT
					String decrypted = decrypted_message.toString();
					textArea.setText("Mensagem descriptografada: " + decrypted + "\n" + "Mensagem Original: " + decoded_message);
					textArea.setEditable(false);
					
					JScrollPane scrollPane = new JScrollPane(textArea);
					
					JOptionPane.showMessageDialog(null, scrollPane);
				} else if (option2 == 2) {
					// ENCRYPT
					String encoded = encoded_message.toString();
					String crypt = encrypted_message.toString();
					textArea.setText("Mensagem codificada: " + encoded + "\n" + "Mensagem cryptografada: " + crypt);
					textArea.setEditable(false);
					
					JScrollPane scrollPane = new JScrollPane(textArea);
					
					JOptionPane.showMessageDialog(null, scrollPane);
				} else {
					generate_again = true;
					receiveMessage = false;
					exit = false;
				}
				
			}
		}
		
		encoded_message = encodeMessage.encode(message);
		encrypted_message = encrypt.encrypt(public_key, encoded_message);
		decrypted_message = decrypt.decrypt(private_key, public_key, encrypted_message);
		decoded_message = decodeMessage.decode(decrypted_message);
		sign_message = sign.sign(private_key, public_key, message, encoded_message);
		
		System.out.println("Chaves Pública: " + public_key[0] + " " + public_key[1]);
		System.out.println("Chaves Privadas: " + private_key[0] + " " + private_key[1] + " " + private_key[2]);
		System.out.println("Message codificada: " + encoded_message);
		System.out.println("Message encryptada: " + encrypted_message);
		System.out.println("Message decryptografada: " + decrypted_message);
		System.out.println("Message decodificada: " + decoded_message);
	}
}