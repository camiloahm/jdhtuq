/*
 *  Communication project implement communication point to point and multicast
 *  Copyright (C) 2010  Daniel Pelaez, Daniel Lopez, Hector Hurtado
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.edu.uniquindio.utils.communication.transfer.structure;

import co.edu.uniquindio.utils.communication.message.Message;
import co.edu.uniquindio.utils.communication.transfer.Responder;

/**
 * The <code>MulticastManagerStructure</code> class is an
 * <code>Communicator</code> and implement for data structure
 * 
 * @author Daniel Pelaez
 * @version 1.0, 17/06/2010
 * @since 1.0
 * 
 */
public class MulticastManagerStructure extends TransferManagerStructure {

	/**
	 * Reference to CommunicationDataStructure
	 */
	private CommunicationDataStructure communicationDataStructure;

	/**
	 * Builds a MulticastManagerStructure
	 * 
	 * @param communicationDataStructure
	 */
	public MulticastManagerStructure(
			CommunicationDataStructure communicationDataStructure,
			Responder responder) {
		super(responder);
		this.communicationDataStructure = communicationDataStructure;
	}

	/**
	 * Not implemented
	 */
	public Message reciever() {
		return null;
	}

	/**
	 * Send message through of CommunicationDataStructure
	 */
	public void send(Message message) {

		if (!responder.releaseResponse(message)) {
			communicationDataStructure.notifyMulticast(message);
		}else {
			communicationDataStructure.notifyMessage(message);
		}
	}

	/**
	 * Not implemented
	 */
	public void stop() {

	}

}