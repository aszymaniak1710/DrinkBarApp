import androidx.lifecycle.ViewModel
import com.example.drinkbarapp.Cocktail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CocktailViewModel : ViewModel() {

    private val _selectedCocktail = MutableStateFlow<Cocktail?>(null)
    val selectedCocktail: StateFlow<Cocktail?> = _selectedCocktail

    fun selectCocktail(cocktail: Cocktail) {
        _selectedCocktail.value = cocktail
    }
}