package vision.voltsofdoom.zapbyte.window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import vision.voltsofdoom.zapbyte.bandwagon.Stowaway;
import vision.voltsofdoom.zapbyte.bandwagon.event.IEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

/**
 * A simple {@link JFrame} to show the status of pre-silverspark-initialised loading.
 * 
 * @author GenElectrovise
 *
 */
public class LoadingWindow extends JFrame implements Runnable {

  /**
   * Updates the status of the window.
   * 
   * @author GenElectrovise
   *
   */
  public static class UpdateStatusEvent implements IEvent {
    private ILoadingWindowStatus status;

    public UpdateStatusEvent(ILoadingWindowStatus status) {
      this.status = status;
    }

    public ILoadingWindowStatus getStatus() {
      return status;
    }
  }

  /**
   * Updates the detailed status of the window.
   * 
   * @author GenElectrovise
   *
   */
  public static class UpdateDetailedStatusEvent implements IEvent {
    private ILoadingWindowDetailedStatus detatiledStatus;

    public UpdateDetailedStatusEvent(ILoadingWindowDetailedStatus status) {
      this.detatiledStatus = status;
    }

    public ILoadingWindowDetailedStatus getDetailedStatus() {
      return detatiledStatus;
    }
  }

  public static LoadingWindow loadingWindow = new LoadingWindow();

  private static final long serialVersionUID = 1L;

  private volatile ILoadingWindowStatus status = ILoadingWindowStatus.OPENING_WINDOW;
  private volatile ILoadingWindowDetailedStatus detailedStatus =
      ILoadingWindowDetailedStatus.NO_ADDITIONAL_INFO;

  private JPanel foregroundPanel = new JPanel();
  private JLabel lblVoltsOfDoom = new JLabel("Volts of Doom");
  private JLabel lblStatus = new JLabel("Status: " + status.getMessage());
  JLabel lblDetailedStatus = new JLabel("Info: ");
  private JLabel lblLoadingPleaseWait = new JLabel("Loading... Please wait...");

  public LoadingWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    updateContents();
  }

  public static void main(String[] args) {
    Thread.currentThread().setName("loading_window");
    new LoadingWindow().run();
  }

  @Override
  public void run() {
    EventQueue.invokeLater(() -> {
      updateContents();
      setVisible(true);
    });
  }

  private void updateContents() {
    // JFrame
    setResizable(false);
    // setBackground(UIManager.getColor("CheckBox.focus"));
    setForeground(UIManager.getColor("Button.focus"));
    setTitle("Volts of Doom - Loading");
    getContentPane().setBackground(UIManager.getColor("Button.background"));
    getContentPane().setForeground(Color.LIGHT_GRAY);
    getContentPane().setLayout(null);
    setBounds(10, 11, 300, 207);

    // Foreground JPanel
    foregroundPanel.setBackground(UIManager.getColor("Button.light"));
    foregroundPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    foregroundPanel.setBounds(10, 11, 274, 156);
    foregroundPanel.setLayout(null);
    lblVoltsOfDoom.setHorizontalAlignment(SwingConstants.CENTER);

    // Name Label
    lblVoltsOfDoom.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 15));
    lblVoltsOfDoom.setBounds(10, 11, 254, 21);

    // Add components
    getContentPane().add(foregroundPanel);
    foregroundPanel.add(lblVoltsOfDoom);

    lblLoadingPleaseWait.setHorizontalAlignment(SwingConstants.CENTER);
    lblLoadingPleaseWait.setBounds(10, 46, 254, 14);
    foregroundPanel.add(lblLoadingPleaseWait);

    lblStatus.setText("Status: " + status.getMessage());
    lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
    lblStatus.setBounds(10, 74, 254, 14);
    foregroundPanel.add(lblStatus);

    lblDetailedStatus.setText("Info: " + detailedStatus.getDetailedMessage());
    lblDetailedStatus.setHorizontalAlignment(SwingConstants.CENTER);
    lblDetailedStatus.setBounds(20, 99, 244, 46);
    foregroundPanel.add(lblDetailedStatus);
  }

  public synchronized void setStatus(ILoadingWindowStatus status) {
    this.status = status;
    updateContents();
  }

  public synchronized void setDetailedStatus(ILoadingWindowDetailedStatus detailedStatus) {
    this.detailedStatus = detailedStatus;
    updateContents();
  }

  @Stowaway
  private static void listenForStatusUpdates(LoadingWindow.UpdateStatusEvent event) {
    loadingWindow.setStatus(event.getStatus());
  }

  @Stowaway
  private static void listenForDetailedStatusUpdates(
      LoadingWindow.UpdateDetailedStatusEvent event) {
    loadingWindow.setDetailedStatus(event.getDetailedStatus());
  }

  public void disableAndDispose() {
    setEnabled(false);
    dispose();
  }
}
