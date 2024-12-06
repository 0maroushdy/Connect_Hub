/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

/**
 *
 * @author moustafa
 */
public class testDataBase {
    public static void main(String[] args) {
        ContentDataBase.getInstance();
        
        ContentDataBase.getInstance().shutDown();
    }
}
