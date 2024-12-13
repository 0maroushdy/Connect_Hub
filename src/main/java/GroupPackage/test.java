/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupPackage;

/**
 *
 * @author moustafa
 */
public class test {
    public static void main(String[] args) {
        System.out.println("group database: " + GroupDataBase.getInstance().getGroups());
        GroupHandler handler = new GroupHandler.Builder("u1")
                .build();
        
        
        
        Group g1 = new Group.Builder("g1", handler)
                .setDescription("hello from g1")
                .setPhotoPath("hehe")
                .build()
                .create();
        
//        g1.getHandler().
    }
}
