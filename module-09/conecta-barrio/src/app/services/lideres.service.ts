import { Injectable, signal,computed } from '@angular/core';

export interface Lider{
     id: number;
     name: string;
     email: string;
     phone: string;
     addressCity: string;
     companyName: string;
}

@Injectable({
  providedIn: 'root',
})
export class LideresService {

  private listaLideres = signal<Lider[]>( [
  {
    "id": 1,
    "name": "Laura Gómez",
    "email": "laura.gomez@example.com",
    "phone": "+57 3001234567",
    "addressCity": "Bogotá",
    "companyName": "TechSolutions"
  },
  {
    "id": 2,
    "name": "Carlos Pérez",
    "email": "carlos.perez@example.com",
    "phone": "+57 3109876543",
    "addressCity": "Medellín",
    "companyName": "Innovar S.A."
  },
  {
    "id": 3,
    "name": "Mariana Torres",
    "email": "mariana.torres@example.com",
    "phone": "+57 3154567890",
    "addressCity": "Cali",
    "companyName": "AgroColombia"
  },
  {
    "id": 4,
    "name": "Andrés Ramírez",
    "email": "andres.ramirez@example.com",
    "phone": "+57 3012349876",
    "addressCity": "Barranquilla",
    "companyName": "Marítima Ltda."
  },
  {
    "id": 5,
    "name": "Sofía Martínez",
    "email": "sofia.martinez@example.com",
    "phone": "+57 3206543210",
    "addressCity": "Cartagena",
    "companyName": "Turismo Caribe"
  },
  {
    "id": 6,
    "name": "Julián Herrera",
    "email": "julian.herrera@example.com",
    "phone": "+57 3187654321",
    "addressCity": "Manizales",
    "companyName": "Café Andino"
  }
]);

lideres = this.listaLideres.asReadonly();


private idsPrioritarios = signal<Set<number>>(new Set());

totalPrioritarios = computed(() => this.idsPrioritarios().size);

    esPrioritario(id:number):boolean{
        return this.idsPrioritarios().has(id);
    }

    alternarPrioritario(id: number){
         this.idsPrioritarios.update(actuales => {
      const nuevos = new Set(actuales);
      if (nuevos.has(id)) {
        nuevos.delete(id);
      } else {
        nuevos.add(id);
      }
      return nuevos;
    });
  }
}
