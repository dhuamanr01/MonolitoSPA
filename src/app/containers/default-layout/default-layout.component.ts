import {Component} from '@angular/core';
import { Router } from '@angular/router';
import { INavData } from '@coreui/angular';
import { MenuService } from 'src/app/_service/menu.service';
import { TOKEN_NAME, TOKEN_ROLE } from 'src/app/_shared/var.constant';
import { navItemsAdmin } from '../../_navAdmin';
import { navItemsUser } from '../../_navUser';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  //public navItems = navItems;
  public navItems : INavData[] = [];

  constructor(private menuService : MenuService, private router: Router){  }

  cerrarSesion(){
    console.log('cerrarSesion');
    //let access_token = sessionStorage.getItem(TOKEN_NAME);
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  ngOnInit(): void {
    var data =  +sessionStorage.getItem(TOKEN_ROLE) ;

    if(data===1){
      this.navItems =  navItemsAdmin;
    }
    else  {
      this.navItems =  navItemsUser;
    }

  }

}
