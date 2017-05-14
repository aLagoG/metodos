package delag.metodosnumericos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, new FragmentHome()).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().findFragmentById(R.id.frame_container) instanceof FragmentHome) {
                super.onBackPressed();
            } else {
                ((NavigationView) findViewById(R.id.nav_view)).getMenu().performIdentifierAction(R.id.inicio, 0);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.inicio) {
            fragment = new FragmentHome();
        } else if (id == R.id.metodo1Yo) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Next Fragment", new FragmentGaussJordan());
            bundle.putSerializable("Strings", new String[]{"Este método recibe una matriz y regresa una matriz equivalente de forma triangular superior",
                    "Puede recibir cualquier matriz que tenga dimensiones mayores o iguales a 2x2",
                    "Es importante tener en cuenta que para matrices muy grandes este proceso puede tardar mucho tiempo"});
            fragment = new FragmentInstructions();
            fragment.setArguments(bundle);
        } else if (id == R.id.metodo2Yo) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Next Fragment", new FragmentGaussSeidel());
            bundle.putSerializable("Strings", new String[]{"Este método recibe la representación matricial de un sistema de ecuaciones lineales y" +
                    " regresa una aproximación a la solución del mismo",
                    "Es importante tener en cuenta que para matrices muy grandes este proceso puede tardar mucho tiempo"});
            fragment = new FragmentInstructions();
            fragment.setArguments(bundle);
        } else if (id == R.id.metodo3Yo) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Next Fragment", new FragmentInterpolationPolynomials());
            bundle.putSerializable("Strings", new String[]{"Este método recibe cierta cantidad de puntos y devuelve un polinomio que pasa por todos ellos"});
            fragment = new FragmentInstructions();
            fragment.setArguments(bundle);
        } else if (id == R.id.metodo1Naka) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Next Fragment", new FragmentCramer());
            bundle.putSerializable("Strings", new String[]{"Este método recibe la representación matricial de un sistema de ecuaciones lineales y" +
                    " regresa una aproximación a la solución del mismo",
                    "Es importante recordar que Cramer tiene la misma cantidad de ecuaciones como variables, es decir, la matriz debe ser cuadrada. Considerar que la cuarta columna"+
                    "representa el lado derecho de la ecuación.",
                    "Es importante tener en cuenta que para matrices muy grandes este proceso puede tardar mucho tiempo"});
            fragment = new FragmentInstructions();
            fragment.setArguments(bundle);
        } else if (id == R.id.metodo2Naka) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Next Fragment", new Biseccion());
            bundle.putSerializable("Strings", new String[]{""});
            fragment = new FragmentInstructions();
            fragment.setArguments(bundle);
        } else if (id == R.id.metodo3Naka) {
            //TODO aqui pones fragment = new "La clase que sea tu fragment" para el metodo 3
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
        }
        ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
