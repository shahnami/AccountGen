/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.model;

/**
 *
 * @author namishah
 */
public class Vehicle {
    private int _year;
    private String _brand;
    private String _model;
    
    public Vehicle(int year, String brand, String model){
        this._year = year;
        this._brand = brand;
        this._model = model;
    }
    
    public Vehicle(){
        //
    }

    public int getYear() {
        return _year;
    }

    public String getBrand() {
        return _brand;
    }

    public String getModel() {
        return _model;
    }

    public void setYear(int _year) {
        this._year = _year;
    }

    public void setBrand(String _brand) {
        this._brand = _brand;
    }

    public void setModel(String _model) {
        this._model = _model;
    }
}
