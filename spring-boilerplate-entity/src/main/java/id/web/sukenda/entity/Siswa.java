package id.web.sukenda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("kesiswaan_siswa")
public class Siswa extends BaseEntity {

    @Indexed(unique = true)
    private String nomorInduk;

    private String namaLengkap;

    private String panggilan;

    private String tempatLahir;

    private String tanggalLahir;

}
