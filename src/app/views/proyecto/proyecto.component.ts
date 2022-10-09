import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Proyecto } from 'src/app/_model/proyecto';
import { Usuario } from 'src/app/_model/usuario';
import { UsuarioService } from 'src/app/_service/usuario.service';
import { ProyectoService } from '../../_service/proyecto.service';
import { ProyectoArchivoService } from '../../_service/proyecto-archivo.service';
import { ProyectoArchivo } from 'src/app/_model/proyectoArchivo';
import { Observable, ReplaySubject } from 'rxjs';
import { TOKEN_ROLE } from 'src/app/_shared/var.constant';

@Component({
  selector: 'app-proyecto',
  templateUrl: './proyecto.component.html',
  styleUrls: ['./proyecto.component.scss']
})
export class ProyectoComponent implements OnInit {

  @ViewChild('myModal') public myModal: ModalDirective;
  @ViewChild('myModalVerMas') public myModalVerMas: ModalDirective;
  @ViewChild('myModalArchivo') public myModalArchivo: ModalDirective;
  @ViewChild('myModalEliminarArchivo') public myModalEliminarArchivo: ModalDirective;

  listProyecto: Proyecto[];
  listUsuario: Usuario[];
  listProyectoArchivo: ProyectoArchivo[];
  proyecto: Proyecto;
  proyectoSelected: Proyecto;

  alertsDismiss: any = [];

  sizeSort: number=10;
  currentPage: number= 1;
  smallnumPages: number;
  totalItems: number; //total de la lista
  maxSize: number = 5; //total de numeros en la paginacion 1,2,3,4,5
  form: FormGroup;
  formVerMas: FormGroup;
  formArchivo: FormGroup;

  edicion: boolean = false;
  edicionArchivo: boolean = false;
  isEstado: boolean = false;
  isProyecto: boolean = true;

  totalItemsArchivo: number; //total de la lista
  currentPageArchivo: number= 1;
  nameFileDelete: string;

  proyectoArchivoUpload: ProyectoArchivo;
  proyectoArchivoDelete: ProyectoArchivo;

  isRoleAdmin:boolean=true;

  constructor(private proyectoService: ProyectoService, private usuarioService: UsuarioService,
    private proyectoArchivoService: ProyectoArchivoService ) {

    this.form = new FormGroup({
      'id' : new FormControl(0),
      'proyecto' : new FormControl(''),
      'descripcion' : new FormControl(''),
      'estado' : new FormControl(''),
      'usuario' : new FormControl(''),
      'fechaExpiracion' : new FormControl('')
    });

    this.formVerMas = new FormGroup({
      'id' : new FormControl(0),
      'proyecto' : new FormControl(''),
      'descripcion' : new FormControl(''),
      'fechaExpiracion' : new FormControl(''),
      'fechaRegistro' : new FormControl(''),
      'fechaModificacion' : new FormControl('')
    });

    this.formArchivo = new FormGroup({
      'file' : new FormControl('')
    });

    this.proyectoArchivoUpload = new ProyectoArchivo();


   }

  ngOnInit(): void {

    this.proyecto = new Proyecto();
    this.cantidadRegistrosLista();
    this.listar();
    this.usuarioService.findAll().subscribe((data:any) => {
      this.listUsuario = data;
    });
    this.roleAdmin();
  }

  roleAdmin(){

    var data =  +sessionStorage.getItem(TOKEN_ROLE) ;
    if(data===1){
      this.isRoleAdmin =  true;
    }
    else  {
      this.isRoleAdmin =  false;
    }

  }

  cantidadRegistrosLista(){
    this.proyectoService.findAll().subscribe(data => {
      this.totalItems=data.length;
    });
  }

  listar(){
    this.proyectoService.page(this.currentPage,this.sizeSort).subscribe((data:any) => {
      console.log(data);
      this.listProyecto=data.content;
    });
  }

  openModalVerMas(objPoyecto: Proyecto){
    console.log(objPoyecto);
    var fechaModificacion = objPoyecto.updated_at;

    if (fechaModificacion === undefined) {
      fechaModificacion= "";
    } else {
      fechaModificacion = fechaModificacion.substring(0, 10);
    }

    this.formVerMas = new FormGroup({
      'id' : new FormControl(objPoyecto.id),
      'proyecto' : new FormControl(objPoyecto.proyecto),
      'descripcion' : new FormControl(objPoyecto.descripcion),
      'fechaExpiracion' : new FormControl(objPoyecto.fecha_expiracion.substring(0, 10)),
      'fechaRegistro' : new FormControl(objPoyecto.created_at.substring(0, 10)),
      'fechaModificacion' : new FormControl(fechaModificacion)
    });

    this.myModalVerMas.show();
  }

  openModalArchivo(obj : any): void {
    this.cleanArchivo();
    this.myModalArchivo.show();
  }

  cleanArchivo(){
    this.formArchivo = new FormGroup({
      'file' : new FormControl('')
    });
  }
  openModalEliminarArchivo(objProyectoArchivo: ProyectoArchivo) {
    this.proyectoArchivoDelete = objProyectoArchivo;
    this.nameFileDelete= objProyectoArchivo.nombre;
    this.myModalEliminarArchivo.show();
  }

  deleteArchivo(){

    this.proyectoArchivoService.delete(this.proyectoArchivoDelete.id).subscribe((data:any) => {
      console.log(data);

      this.proyectoArchivoService.findByProyectoId(this.proyectoSelected.id).subscribe(data =>{
        console.log(data);
        this.listProyectoArchivo=data;
        this.mostrarAlerta("Se eliminó exitosamente.");
        this.myModalEliminarArchivo.hide();
      });


    });

  }

  openModal(objPoyecto: Proyecto){

    if(objPoyecto != null){
      console.log(objPoyecto);
      this.edicion=true;

      this.proyectoService.findById(objPoyecto.id).subscribe(data =>{

        this.form = new FormGroup({
          'id' : new FormControl(data.id),
          'proyecto' : new FormControl(data.proyecto),
          'descripcion' : new FormControl(data.descripcion),
          'estado' : new FormControl(data.estado),
          'usuario' : new FormControl(data.usuario.id),
          'fechaExpiracion' : new FormControl(data.fecha_expiracion.substring(0,10))
        });

        console.log(this.form.controls.estado);
        this.isEstado=true;

      });

    }else {
      this.cleanForm();
    }
    this.myModal.show();
  }

  openArchivo(objPoyecto: Proyecto){

    this.proyectoArchivoService.findByProyectoId(objPoyecto.id).subscribe(data =>{
      this.proyectoSelected= objPoyecto;

      console.log(data);
      this.isProyecto=false;

      if(data!==null){
        this.listProyectoArchivo=data;
      }else{
        this.listProyectoArchivo= new Array();
      }

    });

  }

  save(){

    var usuario = new Usuario();
    usuario.id=this.form.value['usuario'];

    this.proyecto.usuario = usuario;

    this.proyecto.proyecto=this.form.value['proyecto'];
    this.proyecto.descripcion=this.form.value['descripcion'];
    this.proyecto.fecha_expiracion=this.form.value['fechaExpiracion'];
    this.proyecto.id = this.form.value['id'];
    var mensaje = '';

    if(!this.edicion){
      this.proyecto.estado=true;
      this.proyectoService.create(this.proyecto).subscribe(data =>{
        this.listar();
        this.cleanForm();
        mensaje = "Se registró exitosamente."
        this.mostrarAlerta(mensaje);
       });
    } else {
      this.proyecto.estado = Boolean(JSON.parse(this.form.value['estado']));
      this.proyectoService.update(this.proyecto).subscribe(data =>{
        //this.cantidadRegistrosLista();
        this.listar();
        this.cleanForm();
        mensaje = "Se actualizó exitosamente."
        this.edicion=false;
        this.mostrarAlerta(mensaje);
       });

    }
    this.myModal.hide();

  }


  cleanForm(){
    this.proyecto=new Proyecto();
    this.form = new FormGroup({
      'id' : new FormControl(0),
      'proyecto' : new FormControl(''),
      'descripcion' : new FormControl(''),
      'estado' : new FormControl(''),
      'usuario' : new FormControl(''),
      'fechaExpiracion' : new FormControl('')
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

  setPage(pageNo: number): void {
    this.currentPage = pageNo;
  }

  pageChanged(event: any): void {

    console.log('Page changed to: ' + event.page);
    console.log('Number items per page: ' + event.itemsPerPage);
    this.cantidadRegistrosLista();
    this.proyectoService.page( event.page,this.sizeSort).subscribe((data:any) => {
      console.log(data);
      this.listProyecto=data.content;
    });
  }

  backProyeco(){
    this.isProyecto=true;
  }

  downloadFile(objProyectoArchivo: ProyectoArchivo){
    console.log(objProyectoArchivo);

    this.proyectoArchivoService.download(objProyectoArchivo.id).subscribe((data:any) => {
      console.log(data);

      //let _type = this.function("xlsx");
     // const blob = new Blob([data] );

      var file = new Blob([data], {type: 'application/pdf'});
      var url= window.URL.createObjectURL(file);
     // var anchor = document.createElement("a");
      //anchor.download = objProyectoArchivo.nombre;
      //anchor.href = url;
      //anchor.click();
      window.open(url, '_blank');
    });
  }


  function(ext) {
    if (ext != undefined) {
        return this.extToMimes(ext);
    }
    return undefined;
  }

  extToMimes(ext) {
      let type = undefined;
      switch (ext) {
          case 'jpg':
          case 'png':
          case 'jpeg':
              type = 'image/jpeg'
              break;
          case 'txt':
              type = 'text/plain'
              break;
          case 'xls':
              type = 'application/vnd.ms-excel'
              break;
          case 'doc':
              type = 'application/msword'
              break;
          case 'xlsx':
              type = 'application/vnd.ms-excel'
              break;
          default:

      }
      return type;
  }

  base64Output : string;

  onFileSelected(event) {
    /*this.convertFile(event.target.files[0]).subscribe(base64 => {
      this.base64Output = base64;
      console.log(this.base64Output);
    });*/

    var file : File = event.target.files[0];
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    console.log(file);
    reader.onload = (event) => result.next(btoa(event.target.result.toString()));

    result.subscribe(base64 => {
      this.proyectoArchivoUpload = new ProyectoArchivo();
      this.proyectoArchivoUpload.nombre=file.name;
      this.proyectoArchivoUpload.contenido=base64;
    });

  }

  /*convertFile(file : File) : Observable<string> {
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    console.log(file);
    reader.onload = (event) => result.next(btoa(event.target.result.toString()));

    this.proyectoArchivoUpload = new ProyectoArchivo();
    this.proyectoArchivoUpload.nombre=file.name;
    this.proyectoArchivoUpload.contenido=result.toString();

    return result;
  }*/

  saveArchivo(){

    console.log(this.proyectoArchivoUpload);
    var proyectoArchivo = new ProyectoArchivo();

    proyectoArchivo.proyecto= this.proyectoSelected;
    proyectoArchivo.titulo=this.proyectoArchivoUpload.nombre;
    proyectoArchivo.nombre=this.proyectoArchivoUpload.nombre;
    proyectoArchivo.contenido=this.proyectoArchivoUpload.contenido;
    console.log(proyectoArchivo);

    /*var usuario = new Usuario();
    usuario.id=this.form.value['usuario'];

    this.proyecto.usuario = usuario;

    this.proyecto.proyecto=this.form.value['proyecto'];
    this.proyecto.descripcion=this.form.value['descripcion'];
    this.proyecto.fecha_expiracion=this.form.value['fechaExpiracion'];
    this.proyecto.id = this.form.value['id'];*/
    var mensaje = '';

    if(!this.edicionArchivo){

      this.proyectoArchivoService.uploadFile(proyectoArchivo).subscribe(data =>{
        //this.listar();
        //this.cleanForm();

        this.proyectoArchivoService.findByProyectoId(this.proyectoSelected.id).subscribe(data =>{
          console.log(data);
          this.listProyectoArchivo=data;
        });

        this.myModalArchivo.hide();
        mensaje = "Se registró exitosamente."
        this.mostrarAlerta(mensaje);
        this.cleanArchivo();
       });

      /*this.proyecto.estado=true;
      this.proyectoService.create(this.proyecto).subscribe(data =>{
        this.listar();
        this.cleanForm();
        mensaje = "Se agregó exitosamente el registro"
        this.mostrarAlerta(mensaje);
       });*/
    } else {
      /*this.proyecto.estado = Boolean(JSON.parse(this.form.value['estado']));
      this.proyectoService.update(this.proyecto).subscribe(data =>{
        //this.cantidadRegistrosLista();
        this.listar();
        this.cleanForm();
        mensaje = "Se actualizó exitosamente el registro"
        this.edicion=false;
        this.mostrarAlerta(mensaje);
       });*/

    }
    this.myModal.hide();

  }

}
