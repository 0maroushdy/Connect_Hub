/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend.NotificationPackage;

import Backend.UserPackage.User;

/**
 *
 * @author Omar
 */
public interface INotification {
    
    public void Notify();
    public void SendNotification(User user);
//    public void ReceiveNotification();
    public String NotifyContent();
    
    
}
