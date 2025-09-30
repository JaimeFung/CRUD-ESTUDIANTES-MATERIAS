/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ulatina.data;

import java.util.List;

/**
 *
 * @author Esteban
 */
public interface Dao <T>{
    
    List <T> getAll();
    
    void save (T t);
    
    void update (T t);
    
    void delete (T t);
    
}
