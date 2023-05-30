package clases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class JsonEngine {

	private Gson gson;
	

	public JsonEngine() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();;
    }


	public void guardarUsuariosNormales(ColeccionUsuariosNormales usuarios ) throws IOException{

        Writer writer = new FileWriter("UsuariosNormales.json");
        writer.write(gson.toJson(usuarios));
        writer.close();
    } 

	public ColeccionUsuariosNormales cargarUsuariosNormales() throws FileNotFoundException {

    	Reader reader = new FileReader("UsuariosNormales.json");
        Type type = new TypeToken<ColeccionUsuariosNormales>(){}.getType(); 
        return gson.fromJson(reader, type);
    
	} 
	
	public void guardarDuenos(ColeccionDeDuenos duenos) throws IOException{
		Writer writer = new FileWriter("Duenos.json");
		writer.write(gson.toJson(duenos));
		writer.close();
	}	
	public ColeccionDeDuenos cargarDuenos() throws IOException{
		Reader reader = new FileReader("Duenos.json");
        Type type = new TypeToken<ColeccionDeDuenos>(){}.getType(); 
        return gson.fromJson(reader, type);
	}
	public ColeccionDeAdmins cargarAdmins() throws IOException{
		Reader reader = new FileReader("Admins.json");
        Type type = new TypeToken<ColeccionDeAdmins>(){}.getType(); 
        return gson.fromJson(reader, type);
	}
	
	public void guardarHabs(ColeccionHabitaciones hab) throws IOException {
		Writer writer = new FileWriter("Habitacion.json");
		writer.write(gson.toJson(hab));
		writer.close();
	}
	public ColeccionHabitaciones cargarHab() throws IOException{
		Reader reader = new FileReader("Habitacion.json");
        Type type = new TypeToken<ColeccionHabitaciones>(){}.getType(); 
        return gson.fromJson(reader, type);
	}
	
	public void alojamientoTemp(Alojamiento alo) throws IOException {
		Writer writer = new FileWriter("Temporal.json");
		writer.write(gson.toJson(alo));
		writer.close();
	}
	public Alojamiento cargarAlo() throws FileNotFoundException {
		Reader reader = new FileReader("Temporal.json");
        Type type = new TypeToken<Alojamiento>(){}.getType(); 
        return gson.fromJson(reader, type);
	}
	
	public void alojamientoTemp2(Alojamiento alo) throws IOException {
		Writer writer = new FileWriter("Temporal2.json");
		writer.write(gson.toJson(alo));
		writer.close();
	}
	public Alojamiento cargarAlo2() throws FileNotFoundException {
		Reader reader = new FileReader("Temporal2.json");
        Type type = new TypeToken<Alojamiento>(){}.getType(); 
        return gson.fromJson(reader, type);
	}
}
