package utils;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.imageio.ImageIO;

import com.google.common.io.Files;

public class ScreenshotUtil {

    // Ordner, in dem Screenshots gespeichert werden
    private static final String SCREENSHOT_FOLDER = "results/";

    /**
     * Screenshot machen und speichern
     * Der Screenshot-Dateiname enthält:
     * - Testname (z.B. "Checkout")
     * - Seiteninfo (z. B. URL)
     * - Zeitstempel
     */
    public static void takeScreenshot(WebDriver driver, String testName) throws IOException {
        // Screenshot machen
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Zeitstempel für eindeutigen Namen
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Versuche, URL oder Titel der aktuellen Seite als Dateiname zu verwenden
        String pageInfo = "";
        try {
            // URL holen und säubern
            String url = driver.getCurrentUrl();
            pageInfo = url.replaceAll("https?://", "")
                          .replaceAll("[^a-zA-Z0-9-_]", "_");
        } catch (Exception e) {
            pageInfo = "UnknownPage";
        }

        // Ziel-Datei: z. B. results/Checkout_www_saucedemo_com_cart_html_20250607_145210.png
        File dest = new File(SCREENSHOT_FOLDER + testName + "_" + pageInfo + "_" + timestamp + ".png");

        // Screenshot kopieren
        Files.copy(src, dest);

        System.out.println("📸 Screenshot gespeichert: " + dest.getAbsolutePath());
    }

    /**
     * 📄 Alle Screenshots zu einem Test als PDF speichern
     */
    public static void saveScreenshotsAsPDF(String testName) throws IOException {
        File folder = new File(SCREENSHOT_FOLDER);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("⚠️ Screenshot-Ordner nicht gefunden!");
            return;
        }

        // Alle PNG-Dateien zu diesem Testnamen finden
        File[] files = folder.listFiles((dir, name) ->
            name.startsWith(testName) && name.endsWith(".png")
        );

        if (files == null || files.length == 0) {
            System.out.println("⚠️ Keine Screenshots gefunden für: " + testName);
            return;
        }

        // Screenshots sortieren (nach Name = chronologisch)
        Arrays.sort(files, Comparator.comparing(File::getName));

        // Neues PDF-Dokument erstellen
        PDDocument document = new PDDocument();

        for (File file : files) {
            BufferedImage bImage = ImageIO.read(file);
            PDImageXObject pdImage = PDImageXObject.createFromFile(file.getAbsolutePath(), document);

            // Neue Seite mit Bildgröße
            PDPage page = new PDPage(new PDRectangle(bImage.getWidth(), bImage.getHeight()));
            document.addPage(page);

            // Bild auf Seite zeichnen
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.drawImage(pdImage, 0, 0, bImage.getWidth(), bImage.getHeight());
            }
        }

        // PDF-Dateiname
        String pdfFilename = SCREENSHOT_FOLDER + testName + "_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";

        // PDF speichern
        document.save(pdfFilename);
        document.close();

        System.out.println("✅ Screenshots als PDF gespeichert: " + pdfFilename);
    }
}
