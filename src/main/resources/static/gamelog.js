const esc = s => String(s ?? '').replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const reduz = matchMedia('(prefers-reduced-motion: reduce)').matches;

// Navegação ganha borda ao rolar
const nav = document.querySelector('.nav');
addEventListener('scroll', () => nav.classList.toggle('scrolled', scrollY > 8), {passive: true});

// Revela elementos quando entram na tela
const io = new IntersectionObserver(es => es.forEach(e => {
    if (e.isIntersecting) { e.target.classList.add('in'); io.unobserve(e.target); }
}), {threshold: .12});
function observar() { document.querySelectorAll('.reveal:not(.in)').forEach(el => io.observe(el)); }

// Número que conta até o valor final
function contar(el, alvo, dec = 0) {
    if (reduz) { el.textContent = alvo.toFixed(dec); return; }
    const t0 = performance.now();
    (function passo(t) {
        const p = Math.min((t - t0) / 1100, 1);
        el.textContent = (alvo * (1 - Math.pow(1 - p, 4))).toFixed(dec);
        if (p < 1) requestAnimationFrame(passo);
    })(t0);
}

// Luz que segue o mouse no hero
const hero = document.querySelector('.hero');
if (hero && !reduz) hero.addEventListener('pointermove', e => {
    const r = hero.getBoundingClientRect();
    hero.style.setProperty('--mx', (e.clientX - r.left) + 'px');
    hero.style.setProperty('--my', (e.clientY - r.top) + 'px');
});
observar();