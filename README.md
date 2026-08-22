# Calculadora de Pesos Propios

Aplicación de escritorio desarrollada en **JavaFX** para realizar de forma sencilla el cálculo del **peso propio de elementos constructivos** a partir de sus dimensiones y peso específico.

Los valores de referencia utilizados están basados en el **Código Técnico de la Edificación (CTE)**, concretamente en el **DB-SE-AE · Acciones en la edificación**, apartado **2.1 Peso propio** y su **Anejo C**.

## Funcionalidades

* Selección de materiales y elementos constructivos.
* Consulta de los valores mínimos y máximos de peso específico.
* Cálculo automático según el tipo de elemento:

  * Peso por volumen (`kN/m³`).
  * Peso por superficie (`kN/m²`).
  * Peso por metro lineal (`kN/m`).
* Introducción de dimensiones: largo, ancho y espesor.
* Cálculo del peso propio resultante en `kN`.
* Tabla para añadir diferentes elementos y obtener el peso total.
* Validación básica de los datos introducidos.

Los datos de materiales y elementos constructivos se almacenan en:

`src/main/resources/data/materiales.csv`

---

## Ejecutar la aplicación

> **La aplicación es portable y no requiere instalación.**

No es necesario instalar **Java, JavaFX, Maven** ni ninguna otra dependencia para utilizarla.

### 1. Descargar el proyecto

Desde GitHub:

**Code → Download ZIP**

También puede descargarse mediante Git:

```bash
git clone https://github.com/CarlosPalmeroGonzalez/db-se-ae-pesos-propios-version-basica.git
```

Si se descarga como ZIP, simplemente hay que descomprimirlo.

### 2. Localizar el ejecutable

Dentro del proyecto, acceder a:

```text
db-se-ae-pesos-propios-version-basica
└── pesos_propios
    └── target
        └── portable
            └── PesosPropios
                ├── PesosPropios.exe
                ├── app
                └── runtime
```

El ejecutable es:

```text
pesos_propios/target/portable/PesosPropios/PesosPropios.exe
```

### 3. Ejecutar

Hacer doble clic sobre:

**`PesosPropios.exe`**

La aplicación se abrirá directamente.

> **Importante:** las carpetas `app` y `runtime` deben permanecer junto al ejecutable. No es recomendable copiar únicamente `PesosPropios.exe`, ya que forman parte de la versión portable de la aplicación.

---

## Tecnologías

* Java 25
* JavaFX
* Maven
* FXML
* CSS
* Lombok
* CSV

---

## Referencia normativa

**Código Técnico de la Edificación — CTE**

`DB-SE-AE · Seguridad Estructural — Acciones en la edificación`

* Apartado 2.1 — Peso propio
* Anejo C — Prontuario de pesos y coeficientes de rozamiento interno

> Esta aplicación es una herramienta de apoyo para facilitar cálculos sencillos. Los resultados deben contrastarse con la normativa vigente y no sustituyen una comprobación técnica profesional.

---

## Autor

**Carlos Palmero González**

GitHub: `@CarlosPalmeroGonzalez`
