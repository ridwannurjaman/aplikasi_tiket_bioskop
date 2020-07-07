/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop.admin.pageFilm;

/**
 *
 * @author Mochamad
 */
public class Film {
    private int id;
    private String judul;
    private String deskripsi;
    private int durasi;

    public Film(int id, String judul, String deskripsi, int durasi) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.durasi = durasi;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getDurasi() {
        return durasi;
    }
    
    

    
    
    
}
