/*
 *  DHash project implement a storage management
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

package co.edu.uniquindio.dhash.protocol;

import co.edu.uniquindio.utils.communication.message.MessageType;

/**
 * The <code>Protocol</code> class contains all message type for communication
 * protocol in DHash
 *
 * @author Daniel Pelaez
 * @author Hector Hurtado
 * @author Daniel Lopez
 * @version 1.0, 17/06/2010
 * @since 1.0
 */
public final class Protocol {

    /**
     * PUT BEGIN
     */
    public static final MessageType PUT = MessageType.builder()
            .name("PUT")
            .amountParams(PutParams.values().length)
            .build();

    public enum PutParams {
        RESOURCE_KEY, REPLICATE
    }

    public enum PutDatas {
        RESOURCE
    }

    /**
     * PUT END
     */

    /**
     * GET BEGIN
     */
    public static final MessageType GET = MessageType.builder()
            .name("GET")
            .amountParams(GetParams.values().length)
            .build();

    public enum GetParams {
        RESOURCE_KEY
    }

    /**
     * GET END
     */

    /**
     * GET_RESPONSE BEGIN
     */
    public static final MessageType GET_RESPONSE = MessageType.builder()
            .name("GET_RESPONSE")
            .amountParams(GetResponseParams.values().length)
            .build();

    public enum GetResponseParams {
        HAS_RESOURCE
    }

    /**
     * GET_RESPONSE END
     */

    /**
     * RESOURCE_COMPARE BEGIN
     */
    public static final MessageType RESOURCE_COMPARE = MessageType.builder()
            .name("RESOURCE_COMPARE")
            .amountParams(ResourceCompareParams.values().length)
            .build();

    public enum ResourceCompareParams {
        CHECK_SUM, RESOURCE_KEY
    }

    /**
     * RESOURCE_COMPARE END
     */

    /**
     * RESOURCE_COMPARE_RESPONSE BEGIN
     */
    public static final MessageType RESOURCE_COMPARE_RESPONSE = MessageType.builder()
            .name("RESOURCE_COMPARE_RESPONSE")
            .amountParams(ResourceCompareResponseParams.values().length)
            .build();

    public enum ResourceCompareResponseParams {
        EXIST_RESOURCE
    }

    /**
     * RESOURCE_COMPARE_RESPONSE END
     */

    /**
     * TRANSFER_FILES BEGIN
     */
    public static final MessageType RESOURCE_TRANSFER = MessageType.builder()
            .name("RESOURCE_TRANSFER")
            .amountParams(ResourceTransferParams.values().length)
            .build();

    public enum ResourceTransferParams {
        RESOURCE_KEY
    }

    /**
     * TRANSFER_FILES BEGIN
     */
    public static final MessageType RESOURCE_TRANSFER_RESPONSE = MessageType.builder()
            .name("RESOURCE_TRANSFER_RESPONSE")
            .amountParams(0)
            .build();

    public enum ResourceTransferResponseData {
        RESOURCE
    }

    /**
     * TRANSFER_FILES END
     */

    /**
     * TRANSFER_FILES_FINISHED BEGIN
     */
    public static final MessageType TRANSFER_FILES_FINISHED = MessageType.builder()
            .name("TRANSFER_FILES_FINISHED")
            .amountParams(TransferFilesFinishedParams.values().length)
            .build();

    public enum TransferFilesFinishedParams {

    }
    /**
     * TRANSFER_FILES_FINISHED END
     */
}
