package form;

import java.sql.Date;

public class CompaniesData {
    
    private int Id,NoIjin,NoGedung,Rt,Rw,KodePos,NoTelepon;
    private String NamaPerusahaan,Pemilik,JenisUsaha,Provinsi,Kabupaten,Kecamatan,Desa,NamaJalan;
    private Date TanggalBerdiri;
    private String Image;
    
    public CompaniesData() {}

    
    public CompaniesData(int Id, int NoIjin, int NoGedung, int Rt, int Rw, int KodePos,int NoTelepon,
                         String NamaPerusahaan, String Pemilik, String JenisUsaha,
                         String Provinsi, String Kabupaten, String Kecamatan, String Desa,
                         String NamaJalan, Date TanggalBerdiri, String Image){
        
        this.Id = Id;
        this.NoIjin = NoIjin;
        this.NoGedung = NoGedung;
        this.Rt = Rt;
        this.Rw = Rw;
        this.KodePos = KodePos;
        this.NoTelepon = NoTelepon;
        this.NamaPerusahaan = NamaPerusahaan;
        this.Pemilik = Pemilik;
        this.JenisUsaha = JenisUsaha;
        this.Provinsi = Provinsi;
        this.Kabupaten = Kabupaten;
        this.Kecamatan = Kecamatan;
        this.Desa = Desa;
        this.NamaJalan = NamaJalan;
        this.TanggalBerdiri = TanggalBerdiri;
        this.Image = Image;
        
    }
    
    public void setId(int Id)                            { this.Id = Id;        }
    public void setNoIjin(int NoIjin)                    { this.NoIjin = NoIjin;     }
    public void setNoGedung(int NoGedung)                { this.NoGedung = NoGedung;  }
    public void setRt(int Rt)                            { this.Rt = Rt;        }
    public void setRw(int Rw)                            { this.Rw = Rw;        }
    public void setKodePos(int KodePos)                  { this.KodePos = KodePos;   }
    public void setNoTelepon(int NoTelepon)              { this.NoTelepon = NoTelepon; }
    public void setNamaPerusahaan(String NamaPerusahaan) { this.NamaPerusahaan = NamaPerusahaan; }
    public void setPemilik(String Pemilik)               { this.Pemilik = Pemilik;        }
    public void setJenisUsaha(String JenisUsaha)         { this.JenisUsaha = JenisUsaha;     }
    public void setProvinsi(String Provinsi)             { this.Provinsi = Provinsi;       }
    public void setKabupaten(String Kabupaten)           { this.Kabupaten = Kabupaten;      }
    public void setKecamatan(String Kecamatan)           { this.Kecamatan = Kecamatan;      }
    public void setDesa(String Desa)                     { this.Desa = Desa;           }
    public void setNamaJalan(String NamaJalan)           { this.NamaJalan = NamaJalan;      }
    public void setImage(String Image)                   { this.Image = Image;          }
    public void setTanggalBerdiri(Date TanggalBerdiri)   { this.TanggalBerdiri = TanggalBerdiri; }

    
    public int getId()       { return Id;        }
    public int getNoIjin()   { return NoIjin;    }
    public int getNoGedung() { return NoGedung;  }
    public int getRt()       { return Rt;        }
    public int getRw()       { return Rw;        }
    public int getKodePos()  { return KodePos;   }
    public int getNoTelepon(){ return NoTelepon; }
    
    public String getNamaPerusahaan() { return NamaPerusahaan; }
    public String getPemilik()        { return Pemilik;        }
    public String getJenisUsaha()     { return JenisUsaha;     }
    public String getProvinsi()       { return Provinsi;       }
    public String getKabupaten()      { return Kabupaten;      }
    public String getKecamatan()      { return Kecamatan;      }
    public String getDesa()           { return Desa;           }
    public String getNamaJalan()      { return NamaJalan;      }
    public String getImage()          { return Image;          }
    
    public Date getTanggalBerdiri()   { return TanggalBerdiri; }
    
}
