import { Injectable } from '@angular/core';
import { INavData } from '@coreui/angular';
import { Subject } from 'rxjs';
import { Menu } from '../_model/menu';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  menuCambio = new Subject<INavData[]>();
  constructor() { }

}
