import { Cliente } from "./cliente";

export class TipoDocumento{

    public id: number;
    public descripcion: string;
    public createdAt: string;
    public updatedAt: number;
    public clientes: Cliente[];

}