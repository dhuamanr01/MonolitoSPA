import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { INavData } from '@coreui/angular';
import { navItemsAdmin } from '../_navAdmin';
import { navItemsUser } from '../_navUser';
import { TOKEN_NAME, TOKEN_ROLE } from '../_shared/var.constant';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate{
  public navItems : INavData[] = [];

  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log(state.url);

    let token = sessionStorage.getItem(TOKEN_NAME);
    
    if(token=== null){
      sessionStorage.clear();
      this.router.navigate(['login']);
      return false;
    } else {
      let role = +sessionStorage.getItem(TOKEN_ROLE);
      console.log('role: ' + role);

      let url = state.url;
      let cont =0;
      if(role ===1){
        this.navItems =  navItemsAdmin;
      } else  if(role===2){
        this.navItems =  navItemsUser;
      }

      for(let m of this.navItems){
        if(m.url === url){
          console.log("m.url"+ m.url + ", url:"+url);
          cont++;
          break;
        }
      }

      if(cont>0){
        console.log("cont>0"+cont);
        return true;
      } else {
        this.router.navigate(['404']);
        console.log("cont else"+cont);
        return false;
      }

    }


  }

}
