# Pasos #
  1. El sistema muestra los siguientes datos del conejo, permitiendo modificarlos
    * sexo (`*`)
    * fecha de nacimiento (default hoy)
  1. El sistema muestra el estado actual del conejo y una acción por cada posible actividad sobre el mismo que dirige a CambiarEstadoConejo
  1. El sistema muestra la jaula del conejo y una acción que dirige a MudarConejo
  1. El usuario selecciona el nuevo valor de los datos modificables
  1. El usuario confirma la operación.
  1. El sistema verifica que la fecha de nacimiento sea menor que la actual.
  1. El sistema actualiza al conejo.
  1. El sistema muestra un mensaje de éxito

# Flujos alternativos #
> 6.1 El sistema informa que la fecha de nacimiento ingresada es inválida

> 6.2 El sistema regresa al paso 1


> 4.1 El usuario selecciona alguna acción mostrada en el paso 2 o 3.

> 4.2 El sistema se dirige al caso de uso correspondiente.