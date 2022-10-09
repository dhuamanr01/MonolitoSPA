import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Role } from 'src/app/_model/role';
import { Usuario } from 'src/app/_model/usuario';
import { UsuarioService } from 'src/app/_service/usuario.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements OnInit {

  @ViewChild('myModal') public myModal: ModalDirective;

  listUsuario: Usuario[];
  usuario : Usuario;
  alertsDismiss: any = [];

  sizeSort: number=10;
  currentPage: number= 1;
  smallnumPages: number;
  totalItems: number; //total de la lista
  maxSize: number = 5; //total de numeros en la paginacion 1,2,3,4,5
  form: FormGroup;

  edicion: boolean = false;
  isEstado: boolean = false;

  constructor(private usuarioService: UsuarioService ) {
    this.form = new FormGroup({
      'id' : new FormControl(0),
      'userName' : new FormControl(''),
      'password' : new FormControl(''),
      'estado' : new FormControl(true),
      'role' : new FormControl(0),
    });
  }

  ngOnInit(): void {
    this.usuario = new Usuario();
    this.cantidadRegistrosLista();
    this.listar();
  }

  cantidadRegistrosLista(){
    this.usuarioService.findAll().subscribe(data => {
      this.totalItems=data.length;
    });
  }

  openModal(objUsuario: Usuario){

    if(objUsuario != null){
      this.edicion=true;

      this.usuarioService.findById(objUsuario.id).subscribe(data =>{

        this.form = new FormGroup({
          'id' : new FormControl(data.id),
          'userName' : new FormControl(data.user_name),
          'password' : new FormControl(data.password),
          'estado' : new FormControl(data.estado),
          'role' : new FormControl(data.role.id),
        });
        console.log(this.form.controls.estado);
        this.isEstado=true;
      });

    } else {
      this.cleanForm();
    }
    this.myModal.show();
  }

  save(){

    var role = new Role();
    console.log("this.form.value['role']: " +this.form.value['role']);
    role.id = this.form.value['role'];
    this.usuario.user_name = this.form.value['userName'];;
    this.usuario.password = this.form.value['password'];
    this.usuario.role = role;
    this.usuario.id = this.form.value['id'];
    var mensaje = '';

    if(!this.edicion){
      this.usuario.estado = true;

      this.usuarioService.create(this.usuario).subscribe(data =>{
        this.listar();
        this.cleanForm();
        mensaje = "Se registró exitosamente."
        this.mostrarAlerta(mensaje);
       });
    } else {
      this.usuario.estado = Boolean(JSON.parse(this.form.value['estado']));
      this.usuarioService.update(this.usuario).subscribe(data =>{
        this.cantidadRegistrosLista();
        this.listar();
        this.cleanForm();
        mensaje = "Se actualizó exitosamente."
        this.edicion=false;
        this.mostrarAlerta(mensaje);
       });
    }
    this.myModal.hide();
  }

  listar(){
    this.usuarioService.page(this.currentPage,this.sizeSort).subscribe((data:any) => {
      console.log(data);
      this.listUsuario=data.content;
    });
  }

  cleanForm(){
    this.usuario=new Usuario();
    this.form = new FormGroup({
      'id' : new FormControl(0),
      'userName' : new FormControl(''),
      'password' : new FormControl(''),
      'estado' : new FormControl(true),
      'role' : new FormControl(0)
    });
    this.isEstado=false;
  }

  mostrarAlerta(mensaje: string){
    this.alertsDismiss.push({
      type: 'success',
      msg: mensaje,
      timeout: 3500
    });
  }

  isDisabledEstadoMethod(){
    return false;
  }

  setPage(pageNo: number): void {
    console.log(' setPage: ' + pageNo);
    this.currentPage = pageNo;
  }

  pageChanged(event: any): void {
    console.log('Page changed to: ' + event.page);
    console.log('Number items per page: ' + event.itemsPerPage);
    this.cantidadRegistrosLista();
    this.usuarioService.page( event.page,this.sizeSort).subscribe((data:any) => {
      console.log(data);
      this.listUsuario=data.content;
    });
  }

}
