package fr.univavignon.pokedex.api;
import  org.mockito.Mockito;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class IPokedexTest {
    private IPokedex pokedex;
    private Pokemon bulbizarre;
    private List<Pokemon> pokemonList;

    @Before
    public void setUp() throws PokedexException {
        pokedex = Mockito.mock(IPokedex.class);
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon aquali = new Pokemon(134, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);
        pokemonList = new ArrayList<>();
        pokemonList.add(bulbizarre);
        pokemonList.add(aquali);
        Mockito.when(pokedex.size()).thenReturn(pokemonList.size());
        Mockito.when(pokedex.addPokemon(bulbizarre)).thenReturn(0);
        Mockito.when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        Mockito.when(pokedex.getPokemon(134)).thenReturn(aquali);
        Mockito.when(pokedex.getPokemons()).thenReturn(pokemonList);
        Mockito.when(pokedex.getPokemons(Mockito.any(Comparator.class))).thenAnswer(invocation -> {
            pokemonList.sort(invocation.getArgument(0));
            return pokemonList;
        });
    }

    @Test
    public void testSize() {
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        int index = pokedex.addPokemon(bulbizarre);
        assertEquals(0, index);
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertNotNull(retrievedPokemon);
        assertEquals("Bulbizarre", retrievedPokemon.getName());
        assertEquals(613, retrievedPokemon.getCp());
        assertEquals(64, retrievedPokemon.getHp());
        assertEquals(4000, retrievedPokemon.getDust());
        assertEquals(4, retrievedPokemon.getCandy(), 0);
        assertEquals(56.0, retrievedPokemon.getIv(), 0);
        Pokemon retrievedAquali = pokedex.getPokemon(134);
        assertNotNull(retrievedAquali);
        assertEquals("Aquali", retrievedAquali.getName());
        assertEquals(2729, retrievedAquali.getCp());
        assertEquals(202, retrievedAquali.getHp());
        assertEquals(5000, retrievedAquali.getDust());
        assertEquals(4, retrievedAquali.getCandy(), 0);
        assertEquals(100.0, retrievedAquali.getIv(), 0);
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonInvalidId() throws PokedexException {
        Mockito.when(pokedex.getPokemon(999)).thenThrow(new PokedexException("Invalid ID"));
        pokedex.getPokemon(999); // Cela devrait lancer l'exception PokedexException
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(2, pokemons.size());
        assertEquals("Bulbizarre", pokemons.get(0).getName());
        assertEquals("Aquali", pokemons.get(1).getName());
    }

    @Test
    public void testGetPokemonsSorted() {
        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getCp));
        assertEquals("Bulbizarre", sortedPokemons.get(0).getName()); // CP de Bulbizarre = 613
        assertEquals("Aquali", sortedPokemons.get(1).getName());     // CP d'Aquali = 2729
    }
}
