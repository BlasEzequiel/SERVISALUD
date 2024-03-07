/*
    //Actualizar los datos de una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails){
        Persona updatePersona = perService.updatePersona(id, personaDetails);
        if(updatePersona != null){
            return new ResponseEntity<>(updatePersona, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
