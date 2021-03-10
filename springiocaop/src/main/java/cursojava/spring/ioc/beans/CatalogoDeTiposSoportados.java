package cursojava.spring.ioc.beans;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

public class CatalogoDeTiposSoportados {

	// Todos los tipos primitivos
	// Cadenas

	private Character letra;
	private Class clase;
	private Boolean activo;
	private Date fecha;
	private BigDecimal cantidad;
	private File fichero;
	private Locale idioma;
	private Pattern expresion;
	private URL url;
	private String[] matrizCadenas;
	private List<Integer> listaNumeros;
	private Set<String> conjuntoCadenas;
	private Map<String, String> tabla;
	private Properties propiedades;

	public CatalogoDeTiposSoportados() {
	}

	public CatalogoDeTiposSoportados(Character letra, Class clase, Boolean activo, Date fecha, BigDecimal cantidad,
			File fichero, Locale idioma, Pattern expresion, URL url, String[] matrizCadenas, List<Integer> listaNumeros,
			Set<String> conjuntoCadenas, Map<String, String> tabla, Properties propiedades) {
		super();
		this.letra = letra;
		this.clase = clase;
		this.activo = activo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.fichero = fichero;
		this.idioma = idioma;
		this.expresion = expresion;
		this.url = url;
		this.matrizCadenas = matrizCadenas;
		this.listaNumeros = listaNumeros;
		this.conjuntoCadenas = conjuntoCadenas;
		this.tabla = tabla;
		this.propiedades = propiedades;
	}

	public Character getLetra() {
		return letra;
	}

	public void setLetra(Character letra) {
		this.letra = letra;
	}

	public Class getClase() {
		return clase;
	}

	public void setClase(Class clase) {
		this.clase = clase;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public File getFichero() {
		return fichero;
	}

	public void setFichero(File fichero) {
		this.fichero = fichero;
	}

	public Locale getIdioma() {
		return idioma;
	}

	public void setIdioma(Locale idioma) {
		this.idioma = idioma;
	}

	public Pattern getExpresion() {
		return expresion;
	}

	public void setExpresion(Pattern expresion) {
		this.expresion = expresion;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String[] getMatrizCadenas() {
		return matrizCadenas;
	}

	public void setMatrizCadenas(String[] matrizCadenas) {
		this.matrizCadenas = matrizCadenas;
	}

	public List<Integer> getListaNumeros() {
		return listaNumeros;
	}

	public void setListaNumeros(List<Integer> listaNumeros) {
		this.listaNumeros = listaNumeros;
	}

	public Set<String> getConjuntoCadenas() {
		return conjuntoCadenas;
	}

	public void setConjuntoCadenas(Set<String> conjuntoCadenas) {
		this.conjuntoCadenas = conjuntoCadenas;
	}

	public Map<String, String> getTabla() {
		return tabla;
	}

	public void setTabla(Map<String, String> tabla) {
		this.tabla = tabla;
	}

	public Properties getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Properties propiedades) {
		this.propiedades = propiedades;
	}

	@Override
	public String toString() {
		return String.format(
				"CatalogoDeTiposSoportados [letra=%s, clase=%s, activo=%s, fecha=%s, cantidad=%s, fichero=%s, idioma=%s, expresion=%s, url=%s, matrizCadenas=%s, listaNumeros=%s, conjuntoCadenas=%s, tabla=%s, propiedades=%s]",
				letra, clase, activo, fecha, cantidad, fichero, idioma, expresion, url, Arrays.toString(matrizCadenas),
				listaNumeros, conjuntoCadenas, tabla, propiedades);
	}

}
