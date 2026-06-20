package br.com.bolao.backend.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @GetMapping({"", "/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalUsuarios", 1248);
        model.addAttribute("totalPalpites", 15672);
        model.addAttribute("partidasPendentes", 12);
        model.addAttribute("usuariosAtivos", 64);
        model.addAttribute("topRanking", ranking().subList(0, 5));
        return "admin/dashboard";
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        model.addAttribute("ranking", ranking());
        return "admin/ranking";
    }

    @GetMapping("/partidas/{id}/resultado")
    public String formularioResultado(@PathVariable Long id, Model model) {
        model.addAttribute("partida", new PartidaResultado(id, "Brasil", "Argentina", "Final", "16/07/2026 16:00"));
        return "admin/resultadoForm";
    }

    @PostMapping("/partidas/{id}/resultado")
    public String salvarResultado(@PathVariable Long id) {
        return "redirect:/admin/ranking";
    }

    private List<RankingLinha> ranking() {
        return List.of(
                new RankingLinha(1, "Ana Silva", 150, 8, "10/02/2026"),
                new RankingLinha(2, "João Pereira", 150, 6, "08/02/2026"),
                new RankingLinha(3, "Marcos Lima", 135, 7, "15/02/2026"),
                new RankingLinha(4, "Carla Souza", 120, 5, "20/02/2026"),
                new RankingLinha(5, "Pedro Alves", 110, 4, "23/02/2026"),
                new RankingLinha(6, "Lucas Santos", 95, 3, "01/03/2026")
        );
    }

    public record RankingLinha(
            int posicao,
            String nome,
            int pontuacaoTotal,
            int placaresExatos,
            String criadoEm
    ) {
    }

    public record PartidaResultado(
            Long id,
            String selecaoA,
            String selecaoB,
            String fase,
            String dataHora
    ) {
    }
}