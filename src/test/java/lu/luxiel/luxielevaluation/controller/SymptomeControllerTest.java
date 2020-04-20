package lu.luxiel.luxielevaluation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
import lu.luxiel.luxielevaluation.exception.SymptomeNotFoundException;
import lu.luxiel.luxielevaluation.exception.handler.RestResponseEntityExceptionHandler;
import lu.luxiel.luxielevaluation.service.SymptomeService;
import lu.luxiel.luxielevaluation.utils.Helper;

@RunWith(MockitoJUnitRunner.class)
public class SymptomeControllerTest {

	private MockMvc mvc;

	@Mock
	private SymptomeService symptomeService;
	
	@InjectMocks
	private SymptomeController symptomeController;

	@Before
	public void setup() {

		mvc = MockMvcBuilders.standaloneSetup(symptomeController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void getAllSymptomeTestOK() throws Exception {
		List<Symptome> symptomes = Arrays.asList(
				new Symptome("Céphalée"),
				new Symptome("Douleur yeux"),
				new Symptome("Pulsation"),
				new Symptome("Nausées"),
				new Symptome("Vomissements")
				);

		given(symptomeService.getAllSymptomes()).willReturn(symptomes);

		mvc.perform(get("/api/v1/symptome").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(5)))
		.andExpect(jsonPath("$[0].name", is("Céphalée")))
		.andExpect(jsonPath("$[1].name", is("Douleur yeux")))
		.andExpect(jsonPath("$[2].name", is("Pulsation")))
		.andExpect(jsonPath("$[3].name", is("Nausées")))
		.andExpect(jsonPath("$[4].name", is("Vomissements")));

		//Verifier que la methode s'execute une seule fois
		verify(symptomeService, VerificationModeFactory.times(1)).getAllSymptomes();

		//Reinitialisation des données mockées pour le service
		reset(symptomeService);
	}
	
	@Test
	public void getSymptomeByIdTestOK() throws Exception {
		Symptome symptome = new Symptome("Toux");
		symptome.setId(1);
		
		given(symptomeService.getSymptoneById(1)).willReturn(symptome);

		mvc.perform(get("/api/v1/symptome/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Toux")));

		//Verifier que la methode s'execute une seule fois
		verify(symptomeService, VerificationModeFactory.times(1)).getSymptoneById(1);

		//Reinitialisation des données mockées pour le service
		reset(symptomeService);
	}
	
	@Test
	public void getSymptomeByIdNotFoundTestOK() throws Exception {	
		given(symptomeService.getSymptoneById(2)).willThrow(SymptomeNotFoundException.class);

		mvc.perform(get("/api/v1/symptome/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		//Verifier que la methode s'execute une seule fois
		verify(symptomeService, VerificationModeFactory.times(1)).getSymptoneById(2);

		//Reinitialisation des données mockées pour le service
		reset(symptomeService);
	}

	@Test
	public void addSymptomeTestOK() throws Exception {
		Symptome symptome = new Symptome("Toux");
		given(symptomeService.addSymptome(Mockito.any(Symptome.class))).willReturn(symptome);
		System.out.println(Helper.convertObjectToJson(symptome));

		mvc.perform(post("/api/v1/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptome)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", is("Toux")));

		verify(symptomeService, VerificationModeFactory.times(1)).addSymptome(Mockito.any());
		reset(symptomeService);
	}
	
	@Test
	public void addSymptomeAlreadyTestKO() throws Exception {
		Symptome symptome = new Symptome("Vomissements");
		given(symptomeService.addSymptome(Mockito.any(Symptome.class))).willThrow(AlreadyExistEntityException.class);

		mvc.perform(post("/api/v1/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptome)))
		.andExpect(status().isConflict());

		verify(symptomeService, VerificationModeFactory.times(1)).addSymptome(Mockito.any());
		reset(symptomeService);
	}
	
	@Test
	/**
	 * Cas de test ou le symptome est null dans la requête d'ajout d'un symptôme
	 * @throws Exception
	 */
	public void addSymptomeBadRequestWithNullKO() throws Exception {
		Symptome symptome = null;
		given(symptomeService.addSymptome(Mockito.any(Symptome.class))).willThrow(BadRequestException.class);

		mvc.perform(post("/api/v1/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptome)))
		.andExpect(status().isBadRequest());

		//verify(symptomeService, VerificationModeFactory.times(1)).addSymptome(Mockito.any());
		reset(symptomeService);
	}
	
	@Test
	/**
	 * Cas de test ou le champ name du symptome est null dans la requête d'ajout d'un symptôme
	 * @throws Exception
	 */
	public void addSymptomeBadRequestWithNullNameKO() throws Exception {
		Symptome symptome = new Symptome();
		given(symptomeService.addSymptome(Mockito.any(Symptome.class))).willThrow(BadRequestException.class);

		mvc.perform(post("/api/v1/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptome)))
		.andExpect(status().isBadRequest());

		verify(symptomeService, VerificationModeFactory.times(1)).addSymptome(Mockito.any());
		reset(symptomeService);
	}
	
	@Test
	/**
	 * Cas de test d'ajout d'un symptôme avec le champ name du symptome est une chaine vide
	 * @throws Exception
	 */
	public void addSymptomeBadRequestWithEmptyStringNameKO() throws Exception {
		Symptome symptome = new Symptome();
		symptome.setName("");
		given(symptomeService.addSymptome(symptome)).willThrow(BadRequestException.class);

		mvc.perform(post("/api/v1/symptome").contentType(MediaType.APPLICATION_JSON)
				.content(Helper.convertObjectToJson(symptome)))
		.andExpect(status().isBadRequest());

		verify(symptomeService, VerificationModeFactory.times(1)).addSymptome(Mockito.any());
		reset(symptomeService);
	}

}
