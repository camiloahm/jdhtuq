package co.edu.uniquindio.dht.gui.network;


import co.edu.uniquindio.dht.gui.PanelDhash;
import co.edu.uniquindio.storage.StorageException;
import co.edu.uniquindio.storage.StorageNode;
import co.edu.uniquindio.storage.StorageNodeFactory;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

//TODO Documentar
@SuppressWarnings("serial")
@Slf4j
public class NetworkWindow extends JFrame implements WindowListener {
    //TODO Documentar
    private static InetAddress localHost;
    private StorageNodeFactory storageNodeFactory;
    //TODO Documentar
    private PanelDhash panelDhash;
    //TODO Documentar
    private InetAddress inetAddress;
    //TODO Documentar
    public static final String DHASH_CLASS = "co.edu.uniquindio.dhash.node.DHashNodeFactory";
    // TODO Adicionar documentacion

    //TODO Documentar
    static {
        try {
            localHost = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //TODO Documentar
    public NetworkWindow(StorageNodeFactory storageNodeFactory, String resourceDirectory) {
        this(storageNodeFactory, "Network Window", resourceDirectory);
    }

    //TODO Documentar
    public NetworkWindow(StorageNodeFactory storageNodeFactory, String title, String resourceDirectory) {
        setTitle(title);
        setLayout(new BorderLayout());
        addWindowListener(this);

        this.storageNodeFactory = storageNodeFactory;

        panelDhash = new PanelDhash(PanelDhash.NETWORK, this, resourceDirectory);

        add(panelDhash, BorderLayout.CENTER);

        pack();

        setLocationRelativeTo(getParent());

        createNode();
    }

    //TODO Documentar
    private void createNode() {
        try {

            this.inetAddress = InetAddress.getLocalHost();

            if (inetAddress.equals(localHost)) {
                this.inetAddress = getInetAddress();
            }

            StorageNode dHashNode = storageNodeFactory.createNode(
                    inetAddress.getHostName());

            panelDhash.setDHashNode(dHashNode);

        } catch (UnknownHostException e) {
            log.error("Could not initialize the InetAddress", e);
        } catch (StorageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Verifies the network interfaces and returns the appropriate one. This
     * method is used because the ambiguity of the
     * <code>InetAddress.getLocalHost</code> in different operating systems.
     *
     * @return The appropriate InetAddress for the host.
     */
    private InetAddress getInetAddress() {
        try {

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                    .getNetworkInterfaces();

            for (NetworkInterface networkInterface : Collections
                    .list(networkInterfaces)) {

                Enumeration<InetAddress> inetAddressEnum = networkInterface
                        .getInetAddresses();

                for (InetAddress inetAddress : Collections
                        .list(inetAddressEnum)) {

                    if (!inetAddress.equals(localHost)
                            && inetAddress.isSiteLocalAddress()) {
                        return inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            log.error("NetworkInteface error", e);
        }

        return null;
    }

    //TODO Documentar
    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    //TODO Documentar
    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    //TODO Documentar
    @Override
    public void windowClosing(WindowEvent arg0) {
        panelDhash.exit();
        System.exit(0);
    }

    //TODO Documentar
    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    //TODO Documentar
    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    //TODO Documentar
    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    //TODO Documentar
    @Override
    public void windowOpened(WindowEvent arg0) {
    }
}
