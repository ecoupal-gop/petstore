import { Component } from '@angular/core';
import { PetsService } from "../../build/openapi/api/pets.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client-typescript';
  $pets = this.petsService.listPets(10);

  constructor(private readonly petsService: PetsService) {
  }
}
