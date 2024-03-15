package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.CoberturaMedica;
import com.EGG.ServiSalud.Enums.Rol;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente extends Persona {

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "cobertura_medica")
    @Enumerated(EnumType.STRING)
    private CoberturaMedica coberturaMedica;


   /* @ElementCollection
    @CollectionTable(name = "historia_clinica", joinColumns = @JoinColumn(name = "paciente_id"))
    @Column(name = "descripcion")
    private List<String> historiaClinica;*/

    /*En este caso, imagen se mapea como un atributo grande (@Lob)
    @Basic(fetch = FetchType.LAZY) //se usa @Basic(fetch = FetchType.LAZY) para cargar la imagen de forma perezosa,
   lo que significa que la imagen se recuperará solo cuando se acceda a ella.*/
    /*@Lob @Getter @Setter @Column(name="imagen")
    private byte[] imagen;*/

    public Paciente() {
        super();
    }
}
/*@ElementCollection:
Indica que la propiedad a la que se aplica contendrá una colección de elementos básicos o incrustados. En tu caso, parece que historiaClinica es una colección de elementos (String en este caso).
Indica a la JPA que esta colección de elementos no está representada por una entidad separada, sino que forma parte de la entidad actual.

@CollectionTable:
Se utiliza junto con @ElementCollection para especificar el nombre de la tabla que se utilizará para almacenar los elementos de la colección y cómo se relaciona con la entidad principal.
En tu código, se está creando una tabla llamada "historia_clinica" que se utilizará para almacenar los elementos de la colección (descripcion en este caso).
joinColumns especifica cómo se realiza la relación entre la entidad principal y la tabla de la colección. En este caso, se está utilizando una columna llamada "paciente_id" como clave foránea que vincula la tabla de la colección a la entidad principal.

@Column:
Se utiliza para especificar el nombre de la columna que se utilizará para almacenar los elementos individuales de la colección. En tu caso, la colección se compone de elementos de tipo String y cada elemento se almacenará en una columna llamada "descripcion".

@Id:
Indica que el campo al que se aplica es la clave primaria de la entidad.
En el contexto de JPA, la clave primaria es un campo o una combinación de campos que identifica de manera única a cada instancia de la entidad en la base de datos.
La anotación @Id se utiliza para marcar el campo que actuará como clave primaria en la tabla correspondiente.

@Getter:
Es parte del proyecto Lombok y se utiliza para generar automáticamente un método getter para el campo al que se aplica.
Elimina la necesidad de escribir manualmente el código para acceder al valor de un campo (getter).
Simplifica el código y mejora la legibilidad al reducir la cantidad de código "boilerplate" (código repetitivo).

@Setter:
También es parte del proyecto Lombok y se utiliza para generar automáticamente un método setter para el campo al que se aplica.
Elimina la necesidad de escribir manualmente el código para modificar el valor de un campo (setter).
Al igual que @Getter, mejora la legibilidad del código al reducir la cantidad de código repetitivo.*/

