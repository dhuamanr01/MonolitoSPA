import { Proyecto } from "./proyecto";
import { Usuario } from "./usuario";

export class ProyectoArchivo{

    public id: number;
    public proyecto: Proyecto;
    public titulo: string;
    public nombre: string;
    public created_at: string;
    public updated_at: string;
    public contenido: string;

}