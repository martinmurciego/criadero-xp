# Pasos #
  1. El usuario ingresa los siguientes datos del animal:
    * sexo (`*`)
    * fecha de nacimiento (default hoy)
    * jaula (`*`)
    * estado (`*` y sólo debe elegirse entre estados que representen a un conejo sano)
  1. El usuario confirma la operación.
  1. El sistema verifica que la fecha de nacimiento sea menor que la actual.
  1. El sistema verifica que la jaula no esté completa.
  1. El sistema crea un nuevo conejo, ejecuta sobre él la actividad "Compra".
  1. El sistema guarda al conejo en el estado seleccionado y lo añade a la jaula.
  1. El sistema redirige al caso de uso [DetallesConejo](DetallesConejo.md)

# Flujos alternativos #
> 3.1 El sistema informa que la fecha de nacimiento ingresada es inválida

> 3.2 El sistema regresa al paso 1



> 4.1 El sistema informa que la jaula se encuentra llena

> 4.2 El sistema regresa al paso 1