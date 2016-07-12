package br.ufc.quixada.cti.gin.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.ufc.quixada.cti.gin.model.Patrimonio;


public class PDFBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<Patrimonio> listPatrimonios = (List<Patrimonio>) model.get("listaPatrimonios");
				
		doc.setPageSize(PageSize.A4.rotate());
		doc.newPage();
		System.out.println(doc.getPageSize().getRotation());
		
		doc.add(new Paragraph("Lista de Patrimonios"));
		
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 3.0f});
		table.setSpacingBefore(10);
		
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		font.setSize(10);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(5);
		
		
		// write table header 
		cell.setPhrase(new Phrase("Tombamento", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Descrição", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Categoria", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Local", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Situação", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Lotação", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Conservação", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Incorporação", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Chegada Campus", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Comentário", font));
		table.addCell(cell);
		
		// write table row data
		for (Patrimonio p : listPatrimonios) {
			table.addCell(String.valueOf(p.getTombamento()));
			table.addCell(p.getDescricao());
			table.addCell(p.getCategoria().getNome());
			table.addCell(p.getLocal().getFullLocal());
			table.addCell(p.getSituacao().getTipo());
			table.addCell(p.getLotacao().getTipo());
			table.addCell(p.getConservacao().getTipo());
			
			if(p.getIncorporacao() != null){
				table.addCell(p.getIncorporacao().toString());
			}else{
				table.addCell("");
			}
			
			if(p.getChegadaCampus() != null){
				table.addCell(p.getChegadaCampus().toString());
			}else{
				table.addCell("");
			}
			
			if(p.getComentario() != null){
				table.addCell(p.getComentario());
			}else{
				table.addCell("");
			}
		}
		
	doc.add(table);
			
	}

}