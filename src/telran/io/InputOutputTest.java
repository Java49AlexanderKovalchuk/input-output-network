package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputOutputTest {

	@BeforeEach
	void setUp() throws Exception {
	}

		@Test
		void pathTest() {
		Path pathParent = Path.of("../.."); 
		System.out.println(pathParent.toAbsolutePath().normalize().getName(3));
		System.out.println(pathParent.toAbsolutePath());
		System.out.println(pathParent.toAbsolutePath().normalize());
		System.out.println(pathParent.toAbsolutePath().normalize().getName(0));
		System.out.println(pathParent.toAbsolutePath().normalize().getFileName());
		System.out.println(pathParent.toAbsolutePath().normalize().getNameCount());
		
	}
	
	@Test
	void displayDirContentTest() {
		displayDirContent(Path.of("../.."),  3);
		}

	private void displayDirContent(Path path, int depth) {
		
		try {
			if(!Files.isDirectory(path)) {
        		throw new IllegalArgumentException(path.toString() + " is not directory");
        	}
			Files.walk(path, depth)
			.filter(n -> Files.isReadable(n))
			.forEach(n -> {
				int offset = n.toAbsolutePath().normalize().getNameCount();
				int offsetStart = path.toAbsolutePath().normalize().getNameCount();
				String dirOrFile = Files.isDirectory(n) ? "dir" : "file"; 
				System.out.printf("%s%s - %s\n", " ".repeat((offset - offsetStart) * 3), 
						n.toAbsolutePath()
						.normalize().getFileName(), dirOrFile);
				});
		} 

		catch (IOException e) {
			System.out.println(e.toString());
		}
		catch(IllegalArgumentException ex) {
        	System.out.println(ex.toString());
        }
	}
	
}
