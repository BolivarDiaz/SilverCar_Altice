package com.example.bolivarrafael.silvercar.Models;

public class PublishCar {
    String classid;
    String marca;
    String modelo;
    String propietario;
    String numerotelefono;
    String image;
    String precio;


    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getNumerotelefono() {
        return numerotelefono;
    }

    public String getClassid(){

        return classid;
    }

    public String getPrecio(){
        return precio;
    }

    public String getImage(){

        return image;

    }

    public PublishCar(String classid,String marca, String modelo, String propietario, String numerotelefono,String image,String precio){

        this.marca=marca;
        this.modelo=modelo;
        this.propietario=propietario;
        this.numerotelefono=numerotelefono;
        this.image=image;
        this.precio=precio;


    }

    public PublishCar(){



    }







}
