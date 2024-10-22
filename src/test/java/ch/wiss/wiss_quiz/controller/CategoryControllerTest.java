package ch.wiss.wiss_quiz.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ch.wiss.wiss_quiz.model.AnswerRepository;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.QuestionRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

  @MockBean //simuliert DOC
  CategoryRepository categoryRepository;
  @MockBean //simuliert DOC
  QuestionRepository questionRepository;
  @MockBean //simuliert DOC
  AnswerRepository answerRepository;
  
  @Autowired // einbinden SUT
  CategoryController categoryController;
  
  @Autowired // eigentlicher "Testautomat"
  MockMvc mockMvc;
  
  @Test
  public void assertSetupWorks() {
    assertTrue(true);
  }

  @Test
  public void whenValidPostToCategory_thenCorrectResponse() throws Exception {
    // führt korrekten POST-Request zum Einfügen einer neuen Kategorie aus
    mockMvc.perform(MockMvcRequestBuilders.post("/category?name=TEST_CATEGORY"))
    // prüft die System-Antwort
    .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void whenInvalidPostRequestToCategory_thenCorrectResponse() throws Exception {
    // führt POST-Request mit ungültigen Daten aus
    mockMvc.perform(MockMvcRequestBuilders.post("/category/"))
    .andExpect(MockMvcResultMatchers.status().isBadRequest())
    // prüft, ob die Vaidierung aus SQ4B korrekt funktioniert
    .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty())
    .andExpect(MockMvcResultMatchers.content()
    .contentType(MediaType.APPLICATION_JSON));
  }

  /**
	* Wie immer bei Unit-Tests achtest Du auf:
	* sprechende Methodennamen
	* @Test Annotation darüber
	*/
	@Test
	public void whenCategoryControllerInjected_thenNotNull() throws Exception {
	    assertThat(categoryController).isNotNull(); //hier wird nur geprüft, ob unser SUT existiert
	}

  @Test
  public void whenPostRequestToCategoryAndValidCategory_thenCorrectResponse() throws Exception {
      // führt korrekten POST-Request zum Einfügen einer neuen Kategorie aus 
      mockMvc.perform(MockMvcRequestBuilders.post("/category?name=TEST_CATEGORY")) 
      .andExpect(MockMvcResultMatchers.status().isOk()); // prüft die System-Antwort
      /* Hinweis: da keine Datenbank im Hintergrund aktiv ist, wird diese Kategorie NICHT gespeichert */
  }

  @Test
  public void whenPostRequestToCategoryAndInValidCategory_thenCorrectResponse() throws Exception {
      // führt POST-Request mit ungültigen Daten aus
      mockMvc.perform(MockMvcRequestBuilders.post("/category/"))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      // prüft, ob die Vaidierung aus SQ4B korrekt funktioniert
      .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

	@Test
	public void whenGetAllCategories_getValidCategories() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/category"))
    // damit kannst du das eigentliche Ergebnis der Abfrage auf der Konsole ausgeben
    .andDo(res -> System.out.println(res.getResponse().getContentAsString())) 
    .andExpect(MockMvcResultMatchers.status().isOk())		
    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    /* Hinweis: da keine Datenbank im Hintergrund aktiv ist, wird eine leere Liste geliefert. */
  }
}
