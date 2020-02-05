package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if (payload.length > 127) {
			//throw new Exception("for stor payload");
		}
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[128];

		int length = payload.length;

		encoded[0] = (byte) length;

		for (int i = 0; i < length; i++) {
			encoded[i+1] = payload[i];
		}

		//padding
		for (int i = length+1; i < 128; i++) {
			encoded[i] = 0;
		}
		

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		int length = received[0];

		byte[] decoded = new byte[length];

		for (int i = 0; i < length; i++) {
			decoded[i] = received[i+1];
		}

		this.payload = decoded;
		
	}
}
