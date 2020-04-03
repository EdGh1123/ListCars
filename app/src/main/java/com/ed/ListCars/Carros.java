package com.ed.ListCars;

import android.os.Parcel;
import android.os.Parcelable;

public class Carros implements Parcelable {
    private String Marca, Placa, Transmision, fecha ;



    public Carros(Parcel in) {


        Marca = in.readString();
        Transmision = in.readString();
        Placa = in.readString();

        fecha= in.readString();
    }

    public Carros(String Marca, String Placa, String Transmision, String fecha) {


        this.Marca = Marca;
        this.Transmision = Transmision;
        this.Placa = Placa;
   this.fecha=fecha;
    }

    public static final Creator<Carros> CREATOR = new Creator<Carros>() {
        @Override
        public Carros createFromParcel(Parcel in) {
            return new Carros(in);
        }

        @Override
        public Carros[] newArray(int size) {
            return new Carros[size];
        }
    };

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        this.Marca = marca;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        this.Placa = placa;
    }

    public String getTransmision() {
        return Transmision;
    }

    public void setTransmision(String transmision) {
        this.Transmision = transmision;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String genero) {
        this.fecha = fecha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Marca); dest.writeString(Transmision);  dest.writeString(Placa); dest.writeString(fecha);

    }


}
