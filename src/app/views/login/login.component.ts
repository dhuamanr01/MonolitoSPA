import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TOKEN_NAME, TOKEN_ROLE } from 'src/app/_shared/var.constant';
import { AuthenticatedUser } from '../../_model/authenticatedUser';
import { LoginService } from '../../_service/login.service';
import { UsuarioService } from '../../_service/usuario.service';
import { MenuService } from '../../_service/menu.service';
import { Menu } from '../../_model/menu';
import { INavData } from '@coreui/angular';
//import { JwtHelperService } from '@auth0/angular-jwt';


@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent { 

  usuario: string;
  clave: string;

  constructor(private loginService : LoginService,
    private usuarioService : UsuarioService,
    private menuService : MenuService,
    private router: Router) { }

  ngOnInit(): void {
  }

  iniciarSesion(){

    console.log('iniciarSesion');
    console.log('usuario:'+ this.usuario);
    console.log('clave:'+ this.clave);
    var authenticatedUser = new AuthenticatedUser()
    authenticatedUser.user= this.usuario;
    authenticatedUser.password = this.clave;
    this.loginService.login(authenticatedUser).subscribe( (data:any) => {

      console.log(data);

      if(data){
        console.log(data.token);
        //let token= JSON.stringify(data.token);
        sessionStorage.setItem(TOKEN_NAME, data.token);

        this.usuarioService.findByUserName(data.user).subscribe(data => {

          if(data.role.id===1){
            this.router.navigate(['usuario']);
          } else  if(data.role.id===2){
            this.router.navigate(['proyecto']);
          }

          sessionStorage.setItem(TOKEN_ROLE, data.role.id.toString());

        })



      }
    });
  }

}
