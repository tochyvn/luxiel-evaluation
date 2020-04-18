package lu.luxiel.luxielevaluation.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.AlreadyExistEntityException;
import lu.luxiel.luxielevaluation.exception.BadRequestException;
import lu.luxiel.luxielevaluation.exception.MaladieNotFoundException;
import lu.luxiel.luxielevaluation.exception.handler.RestResponseEntityExceptionHandler;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;
import lu.luxiel.luxielevaluation.repository.MaladieRepository;
import lu.luxiel.luxielevaluation.repository.SymptomeRepository;
import lu.luxiel.luxielevaluation.service.MaladieService;
import lu.luxiel.luxielevaluation.service.SymptomeService;
import lu.luxiel.luxielevaluation.utils.Helper;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class MaladieControllerTest {

	private MockMvc mvc;

	@Mock
	private MaladieService maladieService;

	@InjectMocks
	private MaladieController maladieController;

	@Before
	public void setup() {

		mvc = MockMvcBuilders.standaloneSetup(maladieController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void findMaladieBySymptomesTestOK() throws Exception {
		Set<Symptome> symptomes = new HashSet<>();
		symptomes.add(new Symptome("Céphalée"));
		symptomes.add(new Symptome("Douleur yeux"));
		symptomes.add(new Symptome("Fièvre"));
		symptomes.add(new Symptome("Nausées"));

		List<ResultatRecherche> resultats = Arrays.asList(
				new ResultatRecherche("Grippe", 2),
				new ResultatRecherche("Angine", 1),
				new ResultatRecherche("Myopie", 1)
				);

		given(maladieService.findMaladieBySymptome(symptomes)).willReturn(resultats);

		mvc.perform(post("/api/v1/maladie/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptomes))).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].maladieName", is("Grippe")))
		.andExpect(jsonPath("$[1].maladieName", is("Angine"))).andExpect(jsonPath("$[2].maladieName", is("Myopie")));

		// Verifier que la methode s'execute une seule fois
		verify(maladieService, VerificationModeFactory.times(1)).findMaladieBySymptome(symptomes);

		// Reinitialisation des données mockées pour le service
		reset(maladieService);
	}

	@Test
	public void findMaladieBySymptomesNotFoundTestKO() throws Exception {
		Set<Symptome> symptomes = new HashSet<>();
		symptomes.add(new Symptome("Céphalée"));
		symptomes.add(new Symptome("Douleur yeux"));
		symptomes.add(new Symptome("Fièvre"));
		symptomes.add(new Symptome("Nausées"));

		given(maladieService.findMaladieBySymptome(Mockito.any())).willThrow(MaladieNotFoundException.class);

		mvc.perform(post("/api/v1/maladie/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptomes))).andExpect(status().isNotFound());

		// Verifier que la methode s'execute une seule fois
		verify(maladieService, VerificationModeFactory.times(1)).findMaladieBySymptome(symptomes);

		// Reinitialisation des données mockées pour le service
		reset(maladieService);
	}

}
