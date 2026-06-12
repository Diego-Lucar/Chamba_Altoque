package com.project.sistema.chamba_altoque.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "freelancers")
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no debe exceder los 150 caracteres")
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    @Size(max = 200, message = "El correo no debe exceder los 200 caracteres")
    @Column(name = "correo", nullable = false, unique = true, length = 200)
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 15, message = "El teléfono no debe exceder los 15 caracteres")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @NotNull(message = "El distrito es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distrito_id", nullable = false)
    private Distrito distrito;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 255, message = "La contraseña debe tener entre 6 y 255 caracteres")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    @Max(value = 99, message = "Los años de experiencia no pueden exceder 99")
    @Column(name = "experiencia", nullable = false)
    private Integer experiencia;


    @NotNull(message = "Las categorías son obligatorias")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "freelancer_categorias",
        joinColumns = @JoinColumn(name = "freelancer_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @Lob
    @Column(name = "foto_perfil", columnDefinition = "LONGBLOB")
    private byte[] fotoPerfil;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Documento> documentos = new ArrayList<>();

    public Freelancer() {
    }

    public void addDocumento(Documento documento) {
        documentos.add(documento);
        documento.setFreelancer(this);
    }

    public void removeDocumento(Documento documento) {
        documentos.remove(documento);
        documento.setFreelancer(null);
    }

    @Transient
    public String getImagenBase64() {
        if (this.fotoPerfil != null && this.fotoPerfil.length > 0) {
            return Base64.getEncoder().encodeToString(this.fotoPerfil);
        }
        return null;
    }
}
